package MIN.DosiNongBu.auth.filter;

import MIN.DosiNongBu.auth.dto.JwtRequestDto;
import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.service.user.UserAuthService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/*
* 사용자 인증이 필요한 URL 의 경우
* jwt access token 및 refresh token 을 이용한 인증 절차
* 유효성 검사 & 재발급
* */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserAuthService userAuthService;
    private final UserDetailsService userDetailsService;

    // 토큰이 필요한 API URI
    @Value("${jwt.active.url}")
    List<String> activeToken;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String s = request.getRequestURI();

        // 토큰이 필요하지 않은 API URL 의 경우 다음 필터로 이동
        if(!activeToken.contains(request.getRequestURI())){
            filterChain.doFilter(request, response);
            return;
        }

        // Client 에서 API 를 요청 할 때 로컬 스토리지 확인
        String accessToken = request.getHeader("Authorization");

        System.out.println(accessToken);

        // Local Storage 에 Access Token 이 있는 경우
        if(accessToken != null){
            // Access Token 이 유효하는 경우
            if (AccessTokenAuthentication(request, response, filterChain, accessToken)) return;

            // Access Token 이 유효하지 않는 경우, Refresh Token 을 이용하여 재 발급
            // Refresh Token 찾기
            Cookie[] cookies = request.getCookies();
            String refreshToken = null;
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if("refresh_token".equals(cookie.getName())){
                        refreshToken = cookie.getValue();
                        break;
                    }
                }
            }

            // Refresh Token 유효한가?
            if(refreshToken != null & jwtUtil.isTokenValid(refreshToken)){

                String email = jwtUtil.extractClaim(accessToken, Claims::getSubject);
                RoleType role = jwtUtil.extractClaim(accessToken, claims -> claims.get("role", RoleType.class));

                JwtRequestDto jwtRequestDto = JwtRequestDto.builder()
                        .email(email)
                        .role(role)
                        .build();

                accessToken = jwtUtil.RegenerateAccessToken(jwtRequestDto);

                // Access Token 이 유효하는 경우
                if (AccessTokenAuthentication(request, response, filterChain, accessToken)) return;

            }
            else {
                throw new IllegalStateException("다시 로그인 하세요 : 토큰이 유효하지 않습니다.");
            }

        }
        // Access Token 이 없거나 Refresh Token 이 없거나
        else {
            throw new IllegalStateException("다시 로그인 하세요 : 토큰이 없습니다.");
        }
    }

    private boolean AccessTokenAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String accessToken) throws IOException, ServletException {
        if(jwtUtil.isTokenValid(accessToken)){
            // 사용자 아이디 추출
            String loginId = jwtUtil.extractUsername(accessToken);

            // 사용자 아이디가 존재하는 경우
            if(loginId != null && userAuthService.findByEmail(loginId).isPresent()){

                //제공
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
                return true;
            }
            else{
                throw new IllegalStateException("해당 사용자가 없습니다.");
            }
        }
        return false;
    }
}

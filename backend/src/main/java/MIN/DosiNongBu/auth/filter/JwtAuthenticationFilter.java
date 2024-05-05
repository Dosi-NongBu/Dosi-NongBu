package MIN.DosiNongBu.auth.filter;

import MIN.DosiNongBu.auth.dto.JwtRequestDto;
import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.service.user.UserAuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserAuthService userAuthService;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.active.url}")
    List<String> activeToken;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (!activeToken.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("권한이 필요한 요청");

        String accessToken = request.getHeader("Authorization").substring(7);

        if (accessToken != null && AccessTokenAuthentication(request, response, filterChain, accessToken)) {
            return;
        }

        throw new IllegalStateException("다시 로그인 하세요 : 토큰이 없습니다.");
    }

    private boolean AccessTokenAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String accessToken) throws IOException, ServletException {
        try {
            if (jwtUtil.isTokenValid(accessToken)) {
                String loginId = jwtUtil.extractUsername(accessToken);
                if (loginId != null && userAuthService.findByEmail(loginId).isPresent()) {
                    filterChain.doFilter(request, response);
                    return true;
                } else {
                    throw new IllegalStateException("해당 사용자가 없습니다.");
                }
            }
        } catch (ExpiredJwtException e) {

            log.info("accessToken 만료");

            String refreshToken = findRefreshToken(request);
            if (refreshToken != null && jwtUtil.isTokenValid(refreshToken)) {
                Claims claims = e.getClaims();

                String email = claims.getSubject();
                RoleType role = RoleType.valueOf(claims.get("role", String.class));

                JwtRequestDto jwtRequestDto = JwtRequestDto.builder()
                        .email(email)
                        .role(role)
                        .build();

                accessToken = jwtUtil.RegenerateAccessToken(jwtRequestDto);
                response.addHeader("Authorization", "Bearer " + accessToken);

                AccessTokenAuthentication(request, response, filterChain, accessToken);
                return true;
            }
            throw new IllegalStateException("다시 로그인 하세요 : 토큰이 유효하지 않습니다.");
        }
        return false;
    }

    private String findRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

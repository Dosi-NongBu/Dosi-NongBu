/*
package MIN.DosiNongBu.auth.handler;

import MIN.DosiNongBu.auth.dto.JwtRequestDto;
import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.repository.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

*/
/*
* 일반 로그인 성공
* 토큰 생성
* *//*

@Component
@RequiredArgsConstructor
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        String userEmail = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 사용자 역할 확인
        RoleType userRole = RoleType.ROLE_USER;
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ADMIN")) {
                userRole = RoleType.ROLE_ADMIN;
                break;
            }
        }

        // 토큰 생성
        JwtRequestDto jwtRequestDto= JwtRequestDto.builder()
                .email(userEmail)
                .role(userRole)
                .build();

        // Token
        Map<String, String> token = jwtUtil.GenerateToken(jwtRequestDto);
        String accessToken = token.get("accessToken");
        String refreshToken = token.get("refreshToken");

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        response.addHeader("Authorization", "Bearer " + accessToken);

        response.sendRedirect("/");
    }
}
*/

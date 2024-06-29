package MIN.DosiNongBu.controller.auth;

import MIN.DosiNongBu.auth.dto.JwtRequestDto;
import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.controller.auth.dto.JoinRequestDto;
import MIN.DosiNongBu.controller.auth.dto.LoginRequestDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.service.user.UserAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
* 자체 로그인 API
* 회원가입, 로그인, 로그아웃
* */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/auth/join")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid JoinRequestDto joinRequestDto){
        log.info("회원가입");
        userAuthService.join(joinRequestDto);

        return ResponseEntity.ok("Success");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response){
        log.info("로그인");
        // 회원 유무
        User user = userAuthService.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 토큰 생성
        JwtRequestDto jwtRequestDto= JwtRequestDto.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        // Token
        Map<String, String> token = jwtUtil.GenerateToken(jwtRequestDto);
        String accessToken = token.get("accessToken");
        String refreshToken = token.get("refreshToken");

        Cookie refreshTokenCookie  = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie .setHttpOnly(true);
        //cookie.setSecure(true);
        refreshTokenCookie .setPath("/");

        response.setHeader("Set-Cookie", "refresh_token=" + refreshToken + "; HttpOnly; Path=/; SameSite=None");

        Cookie userCookie  = new Cookie("User", user.getUserId().toString());
        userCookie .setHttpOnly(true);
        //cookie2.setSecure(true);
        userCookie .setPath("/");

        response.setHeader("Set-Cookie", "User=" + user.getUserId().toString() + "; HttpOnly; Path=/; SameSite=None");

        response.addCookie(refreshTokenCookie );
        response.addCookie(userCookie );

        response.addHeader("Authorization", "Bearer " + accessToken);

        // 비밀번호 체크
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        userAuthService.login(loginRequestDto);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/auth/logout")
    public String logout(HttpServletResponse response){
        log.info("로그아웃");
        // 로그아웃 시 refresh_token 삭제
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "redirect:/";
    }
}

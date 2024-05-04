package MIN.DosiNongBu.config;

import MIN.DosiNongBu.auth.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginUserAspect {

    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    @Around("@annotation(MIN.DosiNongBu.config.LoginUser)")
    public Object loginUser(ProceedingJoinPoint joinPoint) throws Throwable {
        String accessToken = extractTokenFromHeader(request);

        String loginId = jwtUtil.extractUsername(accessToken);

        Object[] args = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg.getClass() != HttpServletRequest.class)
                .toArray();

        args = Arrays.copyOf(args, args.length + 1);
        args[args.length - 1] = loginId;

        return joinPoint.proceed(args);
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
    }
}

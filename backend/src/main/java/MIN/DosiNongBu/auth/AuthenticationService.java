package MIN.DosiNongBu.auth;


import MIN.DosiNongBu.config.JwtService;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * SecurityHolder 를 사용하여 현재 사용자를 가져오는 로직은 서비스 또는 유틸리티 클래스로 추출하는 것이 좋음
 * 컨트롤러는 보안 관련 로직에 대해 알 필요가 없음
 * */

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // 유저 등록
    public AuthenticationResponse register(RegisterRequest request){

         var user = User.builder()
                 .name()
                 .nickname()
                 .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request
                )
        )
        return null;
    }
}

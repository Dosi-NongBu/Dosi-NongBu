package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.auth.dto.JoinRequestDto;
import MIN.DosiNongBu.controller.auth.dto.LoginRequestDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Long join(JoinRequestDto joinRequestDto) {
        validateDuplicateUser(joinRequestDto.getEmail());

        User user = User.builder()
                .email(joinRequestDto.getEmail())
                .password(passwordEncoder.encode(joinRequestDto.getPassword()))
                .name(joinRequestDto.getName())
                .profileImage(null)
                .nickname(null)
                .currentAddress(null)
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();

        userRepository.save(user);

        return user.getUserId();
    }

    private void validateDuplicateUser(String email){
        userRepository.findByEmail(email)
                .ifPresent(u -> {throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @Override
    public Long login(LoginRequestDto loginRequestDto) {

        return null;
    }
}

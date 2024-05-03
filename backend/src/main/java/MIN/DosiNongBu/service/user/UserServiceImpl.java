package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.UserProfileResponseDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        return;
    }

    @Override
    public UserProfileResponseDto findByEmail(String email) {
        User entity=  userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        return new UserProfileResponseDto(entity);
    }


}

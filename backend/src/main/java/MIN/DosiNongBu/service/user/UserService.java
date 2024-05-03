package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.UserProfileResponseDto;
import MIN.DosiNongBu.domain.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void deleteById(Long id);

    UserProfileResponseDto findByEmail(String email);


}

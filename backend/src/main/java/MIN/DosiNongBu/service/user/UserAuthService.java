package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.auth.dto.JoinRequestDto;
import MIN.DosiNongBu.controller.auth.dto.LoginRequestDto;
import MIN.DosiNongBu.domain.user.User;

import java.util.Optional;

public interface UserAuthService {


    Optional<User> findByEmail(String email);

    void join(JoinRequestDto joinRequestDto);

    void login(LoginRequestDto loginRequestDto);


}

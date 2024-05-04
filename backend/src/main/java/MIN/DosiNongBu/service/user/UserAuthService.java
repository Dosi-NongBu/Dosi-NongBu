package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.auth.dto.JoinRequestDto;
import MIN.DosiNongBu.controller.auth.dto.LoginRequestDto;
import MIN.DosiNongBu.domain.user.User;

import java.util.Optional;

public interface UserAuthService {


    Optional<User> findByEmail(String email);

    Long join(JoinRequestDto joinRequestDto);

    Long login(LoginRequestDto loginRequestDto);


}

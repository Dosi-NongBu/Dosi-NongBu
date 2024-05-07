package MIN.DosiNongBu.auth.dto;

import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtRequestDto {

    private String email;
    private RoleType role;

    @Builder
    public JwtRequestDto(String email, RoleType role) {
        this.email = email;
        this.role = role;
    }
}

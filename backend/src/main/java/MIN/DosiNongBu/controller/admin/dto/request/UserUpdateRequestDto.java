package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private String profileImage;
    private String roleType;

    @Builder
    public UserUpdateRequestDto(String nickname, String profileImage, String roleType) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.roleType = roleType;
    }
}

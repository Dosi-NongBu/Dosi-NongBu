package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private String profileImage;

    @Builder
    public UserUpdateRequestDto(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}

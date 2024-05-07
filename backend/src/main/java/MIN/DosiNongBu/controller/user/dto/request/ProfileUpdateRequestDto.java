package MIN.DosiNongBu.controller.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileUpdateRequestDto {

    private String nickname;
    private String address;
    private String profileImage;

    @Builder
    public ProfileUpdateRequestDto(String nickname, String address, String profileImage) {
        this.nickname = nickname;
        this.address = address;
        this.profileImage = profileImage;
    }
}

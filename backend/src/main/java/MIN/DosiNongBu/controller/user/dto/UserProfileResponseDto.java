package MIN.DosiNongBu.controller.user.dto;

import MIN.DosiNongBu.domain.user.User;
import lombok.Getter;

@Getter
public class UserProfileResponseDto {

    private String name;
    private String nickname;
    private String email;
    private String address;
    private String profileImage;

    public UserProfileResponseDto(User entity) {
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.address = entity.getCurrentAddress();
        this.profileImage = entity.getProfileImage();
    }
}

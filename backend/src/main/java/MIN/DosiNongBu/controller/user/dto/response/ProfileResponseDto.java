package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import lombok.Getter;

@Getter
public class ProfileResponseDto {

    private String name;
    private String nickname;
    private String email;
    private String address;
    private String profileImage;
    private ProviderType provider;

    public ProfileResponseDto(User entity) {
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.address = entity.getCurrentAddress();
        this.profileImage = entity.getProfileImage();
        this.provider = entity.getProvider();
    }
}

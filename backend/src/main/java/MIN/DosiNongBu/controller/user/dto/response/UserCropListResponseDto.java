package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.user.UserCrop;
import lombok.Getter;

@Getter
public class UserCropListResponseDto {
    private Long id;
    private String imageUrl;
    private String nickname;

    public UserCropListResponseDto(UserCrop entity) {
        this.id = entity.getUserCropId();
        this.imageUrl = entity.getImageUrls().get(0);
        this.nickname = entity.getNickname();
    }
}

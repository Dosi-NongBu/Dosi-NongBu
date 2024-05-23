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

        if (entity.getImageUrls() != null && !entity.getImageUrls().isEmpty()) {
            this.imageUrl = entity.getImageUrls().get(0);
        } else {
            this.imageUrl = null;
        }

        this.nickname = entity.getNickname();
    }
}

package MIN.DosiNongBu.controller.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserCropImageSaveRequestDto {

    private List<String> imageUrls;

    @Builder
    public UserCropImageSaveRequestDto(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}

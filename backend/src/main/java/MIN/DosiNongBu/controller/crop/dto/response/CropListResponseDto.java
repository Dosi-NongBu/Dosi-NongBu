package MIN.DosiNongBu.controller.crop.dto.response;

import MIN.DosiNongBu.domain.crop.Crop;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CropListResponseDto {
    private Long id;
    private String name;
    private String imageUrl;

    public CropListResponseDto(Crop entity) {
        this.id = entity.getCropId();
        this.name = entity.getName();

        if (entity.getImageUrls() != null && !entity.getImageUrls().isEmpty()) {
            this.imageUrl = entity.getImageUrls().get(0);
        } else {
            this.imageUrl = null;
        }
    }
}

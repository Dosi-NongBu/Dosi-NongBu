package MIN.DosiNongBu.controller.user.dto.request;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCropLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCropLogSaveRequestDto {

    private CropManageType manage;

    @Builder
    public UserCropLogSaveRequestDto(CropManageType manage) {
        this.manage = manage;
    }

    public UserCropLog toEntity() {
        return UserCropLog.builder()
                .manage(this.manage)
                .build();
    }
}

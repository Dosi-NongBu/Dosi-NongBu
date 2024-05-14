package MIN.DosiNongBu.controller.user.dto.request;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCropLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserCropManageSaveRequestDto {

    private CropManageType manage;
    private LocalDateTime manageDate;

    @Builder
    public UserCropManageSaveRequestDto(CropManageType manage, LocalDateTime manageDate) {
        this.manage = manage;
        this.manageDate = manageDate;
    }

    public UserCropLog toEntity() {
        return UserCropLog.builder()
                .manage(this.manage)
                .manageDate(this.manageDate)
                .build();
    }
}

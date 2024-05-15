package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCropLog;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserCropManageListResponseDto {
    private Long cropLogId;
    private CropManageType manage;
    private LocalDateTime date;

    public UserCropManageListResponseDto(UserCropLog entity) {
        this.cropLogId = entity.getCropLogId();
        this.manage = entity.getManage();
        this.date = entity.getModifiedDate();
    }
}

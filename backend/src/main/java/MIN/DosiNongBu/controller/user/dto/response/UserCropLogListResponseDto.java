package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCropLog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCropLogListResponseDto {
    private Long cropLogId;
    private CropManageType manage;
    private LocalDateTime date;

    public UserCropLogListResponseDto(UserCropLog entity) {
        this.cropLogId = entity.getCropLogId();
        this.manage = entity.getManageType();
        this.date = entity.getModifiedDate();
    }
}

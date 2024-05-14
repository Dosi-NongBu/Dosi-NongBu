package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCropLog;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserCropManageListResponseDto {

    private CropManageType manage;
    private LocalDateTime date;

    public UserCropManageListResponseDto(UserCropLog entity) {
        this.manage = entity.getManage();
        this.date = entity.getManageDate();
    }
}

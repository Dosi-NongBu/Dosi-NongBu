package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserCropAlarm;
import lombok.Getter;

import java.util.List;

@Getter
public class UserCropAlarmResponseDto {

    private Boolean isWaterAlarm;
    private Integer water;

    private Boolean isVentilationAlarm;
    private Integer ventilation;

    private Boolean isRepotAlarm;
    private Integer repot;

    private Boolean isPruningAlarm;
    private Integer pruning;

    public UserCropAlarmResponseDto(UserCrop entity) {

        List<UserCropAlarm> alarmEntity = entity.getUserCropAlarms();
        for(UserCropAlarm userCropAlarm : alarmEntity){
            if (userCropAlarm.getManage() == CropManageType.WATER) {
                this.isWaterAlarm = userCropAlarm.getIsAlarm();
                this.water = userCropAlarm.getPeriod();
                continue;
            } else if (userCropAlarm.getManage() == CropManageType.VENTILATION) {
                this.isVentilationAlarm = userCropAlarm.getIsAlarm();
                this.ventilation = userCropAlarm.getPeriod();
                continue;
            } else if (userCropAlarm.getManage() == CropManageType.REPOT) {
                this.isRepotAlarm = userCropAlarm.getIsAlarm();
                this.repot = userCropAlarm.getPeriod();
                continue;
            } else if (userCropAlarm.getManage() == CropManageType.PRUNING) {
                this.isPruningAlarm = userCropAlarm.getIsAlarm();
                this.pruning = userCropAlarm.getPeriod();
            }
        }
    }
}

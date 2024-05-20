package MIN.DosiNongBu.controller.user.dto.request;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserCropAlarm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserCropAlarmUpdateRequestDto {

    private Boolean isWaterAlarm;
    private Integer water;

    private Boolean isVentilationAlarm;
    private Integer ventilation;

    private Boolean isRepotAlarm;
    private Integer repot;

    private Boolean isPruningAlarm;
    private Integer pruning;

    @Builder
    public UserCropAlarmUpdateRequestDto(Boolean isWaterAlarm, Integer water, Boolean isVentilationAlarm, Integer ventilation, Boolean isRepotAlarm, Integer repot, Boolean isPruningAlarm, Integer pruning) {
        this.isWaterAlarm = isWaterAlarm;
        this.water = water;
        this.isVentilationAlarm = isVentilationAlarm;
        this.ventilation = ventilation;
        this.isRepotAlarm = isRepotAlarm;
        this.repot = repot;
        this.isPruningAlarm = isPruningAlarm;
        this.pruning = pruning;
    }

}

package MIN.DosiNongBu.controller.crop.dto.response;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CropMainResponseDto {

    private String name;
    private Integer difficulty;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer humidity;
    private Integer month;

    private Integer water;
    private Integer ventilation;
    private Integer repot;
    private Integer pruning;

    public CropMainResponseDto(Crop cropEntity) {
        this.name = cropEntity.getName();
        this.difficulty = cropEntity.getDifficulty();
        this.maxTemperature = cropEntity.getMaxTemperature();
        this.minTemperature = cropEntity.getMinTemperature();
        this.humidity = cropEntity.getHumidity();
        this.month = cropEntity.getMonth();

        List<CropPeriod> periodEntity = cropEntity.getCropPeriods();
        for(CropPeriod cropPeriod : periodEntity){
            if (cropPeriod.getManageType() == CropManageType.WATER) {
                this.water = cropPeriod.getPeriod();
                continue;
            } else if (cropPeriod.getManageType() == CropManageType.VENTILATION) {
                this.ventilation = cropPeriod.getPeriod();
                continue;
            } else if (cropPeriod.getManageType() == CropManageType.REPOT) {
                this.repot = cropPeriod.getPeriod();
                continue;
            } else if (cropPeriod.getManageType() == CropManageType.PRUNING) {
                this.pruning = cropPeriod.getPeriod();
            }
        }
    }
}

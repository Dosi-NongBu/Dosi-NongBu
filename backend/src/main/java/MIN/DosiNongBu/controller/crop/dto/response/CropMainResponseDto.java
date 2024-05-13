package MIN.DosiNongBu.controller.crop.dto.response;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
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

        List<CropPeriod> periodEntity = cropEntity.getCropPeriod();
        for(CropPeriod cropPeriod : periodEntity){
            if (cropPeriod.getManage() == CropManageType.WATER) {
                this.water = cropPeriod.getPeriod();
                continue;
            } else if (cropPeriod.getManage() == CropManageType.VENTILATION) {
                this.ventilation = cropPeriod.getPeriod();
                continue;
            } else if (cropPeriod.getManage() == CropManageType.REPOT) {
                this.repot = cropPeriod.getPeriod();
                continue;
            } else if (cropPeriod.getManage() == CropManageType.PRUNING) {
                this.pruning = cropPeriod.getPeriod();
            }
        }
    }
}

package MIN.DosiNongBu.controller.crop.dto.response;

import MIN.DosiNongBu.domain.crop.Crop;
import lombok.Getter;

@Getter
public class CropMainResponseDto {

    private String name;
    private Integer difficulty;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer humidity;
    private Integer month;

    public CropMainResponseDto(Crop entity) {
        this.name = entity.getName();
        this.difficulty = entity.getDifficulty();
        this.maxTemperature = entity.getMaxTemperature();
        this.minTemperature = entity.getMinTemperature();
        this.humidity = entity.getHumidity();
        this.month = entity.getMonth();
    }
}

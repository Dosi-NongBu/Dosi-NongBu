package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManagement;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class NewCropRequestDto {
    private String name;
    private Integer difficulty;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer humidity;
    private Integer month;
    private List<String> imageUrls;

    private String classification;
    private String origin;
    private String feature;

    private String planting;
    private String cultivation;
    private String pest;
    private String tip;
    private String harvestManage;

    private Integer water;
    private Integer ventilation;
    private Integer repot;
    private Integer pruning;

    @Builder
    public NewCropRequestDto(String name, Integer difficulty, Integer maxTemperature, Integer minTemperature, Integer humidity, Integer month, List<String> imageUrls, String classification, String origin, String feature, String planting, String cultivation, String pest, String tip, String harvestManage, Integer water, Integer ventilation, Integer repot, Integer pruning) {
        this.name = name;
        this.difficulty = difficulty;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.humidity = humidity;
        this.month = month;
        this.imageUrls = imageUrls;
        this.classification = classification;
        this.origin = origin;
        this.feature = feature;
        this.planting = planting;
        this.cultivation = cultivation;
        this.pest = pest;
        this.tip = tip;
        this.harvestManage = harvestManage;
        this.water = water;
        this.ventilation = ventilation;
        this.repot = repot;
        this.pruning = pruning;
    }

    public Crop toCropEntity(){
        return Crop.builder()
                .name(name)
                .difficulty(difficulty)
                .maxTemperature(maxTemperature)
                .minTemperature(minTemperature)
                .humidity(humidity)
                .month(month)
                .imageUrls(imageUrls)
                .build();
    }

    public CropInformation toCropInformationEntity(){
        return CropInformation.builder()
                .classification(classification)
                .origin(origin)
                .feature(feature)
                .build();
    }

    public CropManagement toCropManagementEntity(){
        return CropManagement.builder()
                .planting(planting)
                .cultivation(cultivation)
                .pest(pest)
                .tip(tip)
                .harvestManage(harvestManage)
                .build();
    }

    public List<CropPeriod> toCropPeriodEntity() {
        List<CropPeriod> temp = new ArrayList<>();

        temp.add(CropPeriod.builder()
                .manageType(CropManageType.WATER)
                .period(water)
                .build());

        temp.add(CropPeriod.builder()
                .manageType(CropManageType.VENTILATION)
                .period(ventilation)
                .build());

        temp.add(CropPeriod.builder()
                .manageType(CropManageType.REPOT)
                .period(repot)
                .build());

        temp.add(CropPeriod.builder()
                .manageType(CropManageType.PRUNING)
                .period(pruning)
                .build());

        return temp;
    }
}

package MIN.DosiNongBu.controller.crop.dto.response;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManage;
import lombok.Getter;

@Getter
public class CropInfoResponseDto {

    private String classification;
    private String origin;
    private String feature;
    private String planting;
    private String cultivation;
    private String pest;
    private String tip;
    private String harvest_manage;

    public CropInfoResponseDto(CropInformation infoEntity, CropManage manageEntity) {
        this.classification = infoEntity.getClassification();
        this.origin = infoEntity.getOrigin();
        this.feature = infoEntity.getFeature();
        this.planting = manageEntity.getPlanting();
        this.cultivation = manageEntity.getCultivation();
        this.pest = manageEntity.getPest();
        this.tip = manageEntity.getTip();
        this.harvest_manage = manageEntity.getHarvestManage();
    }
}

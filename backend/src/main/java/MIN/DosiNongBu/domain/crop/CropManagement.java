package MIN.DosiNongBu.domain.crop;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS_MANAGES")
public class CropManagement {
    /* PK & FK */
    @Id
    @Column(name = "crop_id")
    private Long cropId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    /* 속성 */
    @Lob
    @Column(name = "plating")
    private String planting;

    @Lob
    @Column(name = "cultivation")
    private String cultivation;

    @Lob
    @Column(name = "pest")
    private String pest;

    @Lob
    @Column(name = "tip")
    private String tip;

    @Lob
    @Column(name = "harvest_manage")
    private String harvestManage;

    @Builder
    public CropManagement(String planting, String cultivation, String pest, String tip, String harvestManage) {
        this.planting = planting;
        this.cultivation = cultivation;
        this.pest = pest;
        this.tip = tip;
        this.harvestManage = harvestManage;
    }
}

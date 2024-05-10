package MIN.DosiNongBu.domain.crop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS_MANAGES")
public class CropManage {
    /* PK & FK */
    @Id
    @Column(name = "crop_id")
    private Long cropId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    /* 속성 */
    @Column(name = "plating")
    private String planting;

    @Column(name = "cultivation")
    private String cultivation;

    @Column(name = "pest")
    private String pest;

    @Column(name = "tip")
    private String tip;

    @Column(name = "harvest_manage")
    private String harvestManage;
}

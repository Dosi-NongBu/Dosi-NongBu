package MIN.DosiNongBu.domain.crop;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS_PERIODS")
public class CropPeriod {
    /* PK & FK */
    @Id
    @Column(name = "crop_id")
    private Long cropId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    /* 속성 */
    @Column(name = "manage")
    private CropManageType manage;

    @Column(name = "period")
    private Integer period;

    @Builder
    public CropPeriod(CropManageType manage, Integer period) {
        this.manage = manage;
        this.period = period;
    }
}

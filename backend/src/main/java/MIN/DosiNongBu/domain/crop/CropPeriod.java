package MIN.DosiNongBu.domain.crop;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS_PERIODS")
public class CropPeriod {
    /* PK */
    @Id
    @Column(name = "crop_period_id")
    private Long cropPeriodId;

    /* FK */
    @JoinColumn(name = "crop_id")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public void setCrop(Crop crop) {
        this.crop = crop;
        crop.getCropPeriod().add(this);
    }
}

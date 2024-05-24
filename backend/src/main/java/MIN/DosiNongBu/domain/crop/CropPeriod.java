package MIN.DosiNongBu.domain.crop;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS_PERIODS")
public class CropPeriod {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_period_id")
    private Long cropPeriodId;

    /* FK */
    @JoinColumn(name = "crop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Crop crop;

    /* 속성 */
    @Column(name = "manage")
    @Enumerated(EnumType.STRING)
    private CropManageType manageType;

    @Column(name = "period")
    private Integer period;

    @Builder
    public CropPeriod(CropManageType manageType, Integer period) {
        this.manageType = manageType;
        this.period = period;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
        crop.getCropPeriod().add(this);
    }
}

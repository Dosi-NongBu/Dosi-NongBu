package MIN.DosiNongBu.domain.crop;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS_INFORMATIONS")
public class CropInformation {
    /* PK & FK */
    @Id
    @Column(name = "crop_id")
    private Long cropId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    /* 속성 */
    @Column(name = "classification")
    private String classification;

    @Column(name = "origin")
    private String origin;

    @Column(name = "feature")
    private String feature;

    @Builder
    public CropInformation(String classification, String origin, String feature) {
        this.classification = classification;
        this.origin = origin;
        this.feature = feature;
    }
}

package MIN.DosiNongBu.domain.crop;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS")
public class Crop {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long cropId;

    /* 연관 */
    @OneToOne(mappedBy = "crop")
    private CropInformation cropInformation;

    @OneToOne(mappedBy = "crop")
    private CropManage cropManage;

    @OneToOne(mappedBy = "crop")
    private CropPeriod cropPeriod;

    /* 속성 */
    @Column(name = "name")
    private String name;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "max_temperature")
    private Integer maxTemperature;

    @Column(name = "min_temperature")
    private Integer minTemperature;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "crop_month")
    private Integer month;

    @ElementCollection
    @CollectionTable(name = "CROPS_IMAGES", joinColumns = @JoinColumn(name = "crop_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @Builder
    public Crop(String name, Integer difficulty, Integer maxTemperature, Integer minTemperature, Integer humidity, Integer month, List<String> imageUrls) {
        this.name = name;
        this.difficulty = difficulty;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.humidity = humidity;
        this.month = month;
        this.imageUrls = imageUrls;
    }

    // 서비스 메서드
    public void addImageUrl(String imageUrl) {
        if (imageUrls.size() < 5) {
            imageUrls.add(imageUrl);
        } else {
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }
    }
}

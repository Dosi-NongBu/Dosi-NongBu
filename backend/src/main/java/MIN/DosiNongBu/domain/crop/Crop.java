package MIN.DosiNongBu.domain.crop;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CROPS")
public class Crop {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long cropId;

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

    @Builder
    public Crop(String name, Integer difficulty, Integer maxTemperature, Integer minTemperature, Integer humidity, Integer month) {
        this.name = name;
        this.difficulty = difficulty;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.humidity = humidity;
        this.month = month;
    }
}

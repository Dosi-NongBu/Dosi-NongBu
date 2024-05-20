package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.crop.Crop;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS_CROPS")
@Entity
public class UserCrop extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_crop_id")
    private Long userCropId;

    /* FK */
    @JoinColumn(name = "user_place_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserPlace userPlace;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "crop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Crop crop;

    /* 연관 */
    @OneToMany(mappedBy = "userCrop", fetch = FetchType.LAZY)
    private List<UserCropLog> userCropLogs = new ArrayList<>();

    @OneToMany(mappedBy = "userCrop", fetch = FetchType.LAZY)
    private List<UserCropAlarm> userCropAlarms = new ArrayList<>();

    /* 속성 */
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "period")
    private Integer period;

    @Column(name = "previous_period")
    private Integer prePeriod;

    @Column(name = "max_temperature")
    private Integer maxTemperature;

    @Column(name = "min_temperature")
    private Integer minTemperature;

    @Column(name = "humidity")
    private Integer humidity;

    @ElementCollection
    @CollectionTable(name = "USERS_CROPS_IMAGES", joinColumns = @JoinColumn(name = "user_crop_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @Builder
    public UserCrop(String name, String nickname, Integer period, Integer prePeriod, Integer maxTemperature, Integer minTemperature, Integer humidity) {
        this.name = name;
        this.nickname = nickname;
        this.period = period;
        this.prePeriod = prePeriod;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.humidity = humidity;
    }

    public void setUser(User user) {
        if(user.getUserCrops().size() < 10){
            this.user = user;
            user.getUserCrops().add(this);
        }
        else{
            throw new IllegalStateException("사용자 작물은 최대 10개까지만 저장할 수 있습니다.");
        }
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

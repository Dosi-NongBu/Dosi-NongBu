package MIN.DosiNongBu.domain.user;

import jakarta.persistence.*;
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
public class UserCrop {
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

    /* 속성 */
    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @CreatedDate
    @Column(name = "start_date")
    private LocalDateTime createdDate;

    @Column(name = "period")
    private Integer period;

    @Column(name = "previous_period")
    private Integer perPeriod;

    @ElementCollection
    @CollectionTable(name = "USERS_CROPS_IMAGES", joinColumns = @JoinColumn(name = "user_crop_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
        user.getUserCrops().add(this);
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

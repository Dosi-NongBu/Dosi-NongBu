package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS_CROPS_LOGS")
public class UserCropLog {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_log_id")
    private Long cropLogId;

    /* FK */
    @JoinColumn(name = "user_crop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserCrop userCrop;

    /* 속성 */
    @Column(name = "manage")
    private CropManageType manage;

    @CreatedDate
    @Column(name = "manage_date")
    private LocalDateTime manageDate;

    @Builder
    public UserCropLog(CropManageType manage, LocalDateTime manageDate) {
        this.manage = manage;
        this.manageDate = manageDate;
    }

    public void setUserCrop(UserCrop userCrop) {
        this.userCrop = userCrop;
        userCrop.getUserCropLogs().add(this);
    }
}

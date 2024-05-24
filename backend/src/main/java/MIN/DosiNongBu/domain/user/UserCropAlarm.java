package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS_CROPS_ALARMS")
public class UserCropAlarm {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_crop_alarm_id")
    private Long userCropAlarmId;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_crop_id")
    private UserCrop userCrop;

    /* 속성 */
    @Column(name = "manage")
    @Enumerated(EnumType.STRING)
    private CropManageType manageType;

    @Column(name = "period")
    private Integer period;

    @Column(name = "is_alarm")
    private Boolean isAlarm;

    @Builder
    public UserCropAlarm(CropManageType manageType, Integer period, Boolean isAlarm) {
        this.manageType = manageType;
        this.period = period;
        this.isAlarm = isAlarm;
    }

    public void setUserCrop(UserCrop userCrop) {
        this.userCrop = userCrop;
        userCrop.getUserCropAlarms().add(this);
    }

    public void update(Boolean isAlarm, Integer period){
        this.isAlarm = isAlarm;
        this.period = period;
    }



}

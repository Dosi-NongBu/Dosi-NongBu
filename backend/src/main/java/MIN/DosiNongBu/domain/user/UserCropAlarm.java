package MIN.DosiNongBu.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS_CROPS_ALARMS")
public class UserCropAlarm {
    /* PK & FK */
    @Id
    @Column(name = "user_crop_id")
    private Long userCropId;

    @MapsId
    @OneToOne
    private UserCrop userCrop;

    /* 속성 */




}

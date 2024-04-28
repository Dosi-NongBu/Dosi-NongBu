package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS")
@Entity
public class User extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /* 속성 */
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address")
    private String currentAddress;

    /* 생성자 */
    @Builder
    public User(String name, String nickname, String email, String profileImage, String phone, String currentAddress, RoleType roleType) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.phone = phone;
        this.currentAddress = currentAddress;
        this.roleType = roleType;
    }

    public User update(String name, String profileImage){
        this.name = name;
        this.profileImage = profileImage;
        return this;
    }


}

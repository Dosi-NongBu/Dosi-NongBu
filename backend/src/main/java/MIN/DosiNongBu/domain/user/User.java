package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
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
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "profile_image", nullable = true)
    private String profileImage;

    @Column(name = "nickname", nullable = true)
    private String nickname;

    @Column(name = "address", nullable = true)
    private String currentAddress;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(name = "provider", nullable = true)
    @Enumerated(EnumType.STRING)
    private ProviderType provider;


    /* 생성자 */
    @Builder
    public User(String email, String password, String name, String profileImage, RoleType role, ProviderType provider, String nickname, String currentAddress) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.role = role;
        this.provider = provider;
        this.nickname = nickname;
        this.currentAddress = currentAddress;
    }

    public User update(String name, String profileImage){
        this.name = name;
        this.profileImage = profileImage;
        return this;
    }

    public void update(String nickname, String address, String profileImage){
        this.nickname = nickname;
        this.currentAddress = address;
        this.profileImage = profileImage;
    }


}

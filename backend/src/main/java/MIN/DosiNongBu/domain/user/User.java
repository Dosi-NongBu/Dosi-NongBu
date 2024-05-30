package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS")
@Entity
public class User extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /* 연관 */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPlace> userPlaces = new ArrayList<UserPlace>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserCrop> userCrops = new ArrayList<UserCrop>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<Post>();

    /* 속성 */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "address")
    private String currentAddress;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(name = "provider")
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

    // OAuth2 를 이용한 소셜 로그인을 위한 Profile Update
    public User update(String name, String profileImage){
        this.name = name;
        this.profileImage = profileImage;
        return this;
    }

    // 사용자의 요청에 의한 Profile Update
    public void update(String nickname, String address, String profileImage){
        this.nickname = nickname;
        this.currentAddress = address;
        this.profileImage = profileImage;
    }

    // 관리자의 요청에 의한 정보 수정
    public void update(String nickname, String profileImage, RoleType roleType){
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.role = roleType;
    }
}

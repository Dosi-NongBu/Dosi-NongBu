package MIN.DosiNongBu.controller.admin.dto.response;

import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String profileImage;
    private String nickname;
    private String currentAddress;
    private String email;
    private LocalDateTime createdDate;
    private RoleType roleType;

    public UserResponseDto(User entity) {
        this.id = entity.getUserId();
        this.name = entity.getName();
        this.profileImage = entity.getProfileImage();
        this.nickname = entity.getNickname();
        this.currentAddress = entity.getCurrentAddress();
        this.email = entity.getEmail();
        this.createdDate = entity.getCreatedDate();
        this.roleType = entity.getRole();
    }
}

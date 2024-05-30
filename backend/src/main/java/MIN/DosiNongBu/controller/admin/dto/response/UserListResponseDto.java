package MIN.DosiNongBu.controller.admin.dto.response;

import MIN.DosiNongBu.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserListResponseDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private LocalDateTime createdDate;

    public UserListResponseDto(User entity) {
        this.id = entity.getUserId();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.createdDate = entity.getCreatedDate();
    }
}

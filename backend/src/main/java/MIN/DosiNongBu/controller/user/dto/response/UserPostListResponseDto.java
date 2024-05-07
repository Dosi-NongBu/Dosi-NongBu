package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPostListResponseDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String nickname;
    private String name;
    private LocalDateTime modifiedDate;
    private Long good;
    private Long bad;


    public UserPostListResponseDto(Post entity) {
        this.id = entity.getPostId();
        this.title = entity.getTitle();
        this.imageUrl = entity.getImageUrls().get(0);
        this.nickname = entity.getUser().getNickname();
        this.name = entity.getUser().getName();
        this.modifiedDate = entity.getModifiedDate();
        this.good = entity.getGood();
        this.bad = entity.getBad();
    }
}

package MIN.DosiNongBu.controller.post.dto.response;

import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.constant.PostType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private PostType postType;
    private String title;
    private String content;
    private Long good;
    private Long bad;
    private List<String> imageUrls;

    public PostResponseDto(Post entity) {
        this.postType = entity.getPostType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.good = entity.getGood();
        this.bad = entity.getBad();
        this.imageUrls = entity.getImageUrls();
    }
}

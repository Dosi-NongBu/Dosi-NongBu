package MIN.DosiNongBu.controller.post.dto.response;

import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.constant.PostType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostListResponseDto {

    private Long id;
    private PostType postType;
    private String author;
    private String profileImage;
    private String title;
    private String content;
    private Long good;
    private Long bad;
    private String imageUrl;

    public PostListResponseDto(Post entity) {
        this.id = entity.getPostId();


        if(entity.getUser().getNickname()==null){
            this.author = entity.getUser().getName();
        }
        else{
            this.author = entity.getUser().getNickname();
        }

        this.profileImage = entity.getUser().getProfileImage();
        this.postType = entity.getPostType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.good = entity.getGood();
        this.bad = entity.getBad();

        if (entity.getImageUrls() != null && !entity.getImageUrls().isEmpty()) {
            this.imageUrl = entity.getImageUrls().get(0);
        } else {
            this.imageUrl = null;
        }
    }
}

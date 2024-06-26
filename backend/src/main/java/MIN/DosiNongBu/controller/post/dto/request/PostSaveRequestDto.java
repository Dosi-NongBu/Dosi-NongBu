package MIN.DosiNongBu.controller.post.dto.request;

import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.constant.PostType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String content;
    private List<String> imageUrls;

    @Builder
    public PostSaveRequestDto(String title, String content, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }

    public Post toEntity(PostType postType){
        if(imageUrls.size()>5){
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }

        return Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .imageUrls(imageUrls)
                .build();
    }
}

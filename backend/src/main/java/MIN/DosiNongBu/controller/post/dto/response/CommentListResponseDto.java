package MIN.DosiNongBu.controller.post.dto.response;

import MIN.DosiNongBu.domain.post.Comment;
import MIN.DosiNongBu.domain.post.constant.PostType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentListResponseDto {

    private Long id;
    private String author;
    private String profileImage;
    private String content;
    private Long good;
    private Long bad;

    public CommentListResponseDto(Comment entity) {
        this.id = entity.getCommentId();

        if(entity.getUser().getNickname()==null){
            this.author = entity.getUser().getName();
        }
        else{
            this.author = entity.getUser().getNickname();
        }

        this.profileImage = entity.getUser().getProfileImage();
        this.content = entity.getContent();
        this.good = entity.getGood();
        this.bad = entity.getBad();
    }
}

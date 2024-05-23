package MIN.DosiNongBu.controller.post.dto.request;

import MIN.DosiNongBu.domain.post.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;

    @Builder
    public CommentSaveRequestDto(String content) {
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}

package MIN.DosiNongBu.controller.post.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private String content;

    @Builder
    public CommentUpdateRequestDto(String content) {
        this.content = content;
    }


}

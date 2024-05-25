package MIN.DosiNongBu.controller.help.dto.response;

import MIN.DosiNongBu.domain.help.Faq;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FaqListResponseDto {

    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    public FaqListResponseDto(Faq entity) {
        this.id = entity.getFaqId();
        this.title = entity.getFaqAnswer();
        this.modifiedDate = entity.getModifiedDate();
    }
}

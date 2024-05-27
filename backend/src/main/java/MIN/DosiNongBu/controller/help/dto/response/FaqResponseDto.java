package MIN.DosiNongBu.controller.help.dto.response;

import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FaqResponseDto {

    private FaqType faqType;
    private String question;
    private String answer;
    private List<String> imageUrls;

    public FaqResponseDto(Faq entity) {
        this.faqType = entity.getFaqType();
        this.question = entity.getFaqQuestion();
        this.answer = entity.getFaqAnswer();
        this.imageUrls = entity.getImageUrls();
    }
}

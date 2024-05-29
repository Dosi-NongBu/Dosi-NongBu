package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.help.constant.FaqType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FaqUpdateRequestDto {
    private FaqType faqType;
    private String question;
    private String answer;
    private List<String> imageUrls;

    @Builder
    public FaqUpdateRequestDto(FaqType faqType, String question, String answer, List<String> imageUrls) {
        this.faqType = faqType;
        this.question = question;
        this.answer = answer;
        this.imageUrls = imageUrls;
    }
}

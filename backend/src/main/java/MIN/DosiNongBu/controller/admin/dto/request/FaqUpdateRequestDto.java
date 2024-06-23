package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.aop.EnumValue;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FaqUpdateRequestDto {

    @EnumValue(enumClass = FaqType.class, message = "FAQ 카테고리가 잘못되었습니다.", ignoreCase = true)
    private String faqType;

    private String question;
    private String answer;
    private List<String> imageUrls;

    @Builder
    public FaqUpdateRequestDto(String faqType, String question, String answer, List<String> imageUrls) {
        this.faqType = faqType;
        this.question = question;
        this.answer = answer;
        this.imageUrls = imageUrls;
    }
}

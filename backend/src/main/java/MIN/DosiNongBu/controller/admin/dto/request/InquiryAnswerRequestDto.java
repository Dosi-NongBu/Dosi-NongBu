package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryAnswerRequestDto {
    private InquiryStatusType inquiryStatusType;
    private String answer;

    @Builder
    public InquiryAnswerRequestDto(InquiryStatusType inquiryStatusType, String answer) {
        this.inquiryStatusType = inquiryStatusType;
        this.answer = answer;
    }
}

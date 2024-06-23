package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.aop.EnumValue;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryAnswerRequestDto {

    @EnumValue(enumClass = InquiryStatusType.class, message = "문의 상태가 잘못되었습니다.", ignoreCase = true)
    private String inquiryStatusType;

    private String answer;

    @Builder
    public InquiryAnswerRequestDto(String inquiryStatusType, String answer) {
        this.inquiryStatusType = inquiryStatusType;
        this.answer = answer;
    }
}

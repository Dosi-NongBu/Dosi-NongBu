package MIN.DosiNongBu.controller.help.dto.request;

import MIN.DosiNongBu.aop.EnumValue;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InquiryUpdateRequestDto {

    @EnumValue(enumClass = InquiryType.class, message = "문의 카테고리가 잘못되었습니다.", ignoreCase = true)
    private String inquiryType;

    private String title;
    private String content;
    private List<String> imageUrls;

    @Builder
    public InquiryUpdateRequestDto(String inquiryType, String title, String content, List<String> imageUrls) {
        this.inquiryType = inquiryType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }
}

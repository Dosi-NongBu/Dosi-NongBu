package MIN.DosiNongBu.controller.help.dto.response;

import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class InquiryResponseDto {

    private InquiryType inquiryType;
    private String title;
    private String content;
    private List<String> imageUrls;
    private InquiryStatusType inquiryStatusType;
    private String inquiryAnswer;

    public InquiryResponseDto(Inquiry entity) {
        this.inquiryType = entity.getInquiryType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.imageUrls = entity.getImageUrls();
        this.inquiryStatusType = entity.getInquiryStatusType();
        this.inquiryAnswer = entity.getInquiryAnswer();
    }
}

package MIN.DosiNongBu.controller.help.dto.response;

import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class InquiryListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
    private InquiryType inquiryType;

    public InquiryListResponseDto(Inquiry entity) {
        this.id = entity.getInquiryId();
        this.title = entity.getTitle();
        this.author = (entity.getUser().getNickname() != null) ? entity.getUser().getNickname() : entity.getUser().getName();
        this.modifiedDate = entity.getModifiedDate();
        this.inquiryType = entity.getInquiryType();
    }
}

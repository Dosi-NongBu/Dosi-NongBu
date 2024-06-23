package MIN.DosiNongBu.controller.help.dto.request;

import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InquirySaveRequestDto {

    private String title;
    private String content;
    private List<String> imageUrls;

    @Builder
    public InquirySaveRequestDto(String title, String content, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }

    public Inquiry toEntity(String inquiryType){
        return Inquiry.builder()
                .inquiryType(InquiryType.valueOf(inquiryType))
                .title(title)
                .content(content)
                .imageUrls(imageUrls)
                .build();
    }
}

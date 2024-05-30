package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FaqSaveRequestDto {

    private FaqType faqType;
    private String question;
    private String answer;
    private List<String> imageUrls;

    @Builder
    public FaqSaveRequestDto(FaqType faqType, String question, String answer, List<String> imageUrls) {
        this.faqType = faqType;
        this.question = question;
        this.answer = answer;
        this.imageUrls = imageUrls;
    }

    public Faq toEntity(){
        if(imageUrls != null && imageUrls.size()>5){
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }

        return Faq.builder()
                .faqType(faqType)
                .faqQuestion(question)
                .faqAnswer(answer)
                .imageUrls(imageUrls)
                .build();
    }
}

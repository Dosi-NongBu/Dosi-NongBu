package MIN.DosiNongBu.controller.help.dto.response;

import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {

    private NoticeType noticeType;
    private String title;
    private String content;
    private List<String> imageUrls;

    public NoticeResponseDto(Notice entity) {
        this.noticeType = entity.getNoticeType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.imageUrls = entity.getImageUrls();
    }
}

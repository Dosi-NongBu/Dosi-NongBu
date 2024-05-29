package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.help.constant.NoticeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {

    private NoticeType noticeType;
    private String title;
    private String content;
    private List<String> imageUrls;

    @Builder
    public NoticeUpdateRequestDto(NoticeType noticeType, String title, String content, List<String> imageUrls) {
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }
}

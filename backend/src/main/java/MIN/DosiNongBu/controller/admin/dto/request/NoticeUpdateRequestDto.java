package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.aop.EnumValue;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {

    @EnumValue(enumClass = NoticeType.class, message = "공지사항 카테고리가 잘못되었습니다.", ignoreCase = true)
    private String noticeType;

    private String title;
    private String content;
    private List<String> imageUrls;

    @Builder
    public NoticeUpdateRequestDto(String noticeType, String title, String content, List<String> imageUrls) {
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }
}

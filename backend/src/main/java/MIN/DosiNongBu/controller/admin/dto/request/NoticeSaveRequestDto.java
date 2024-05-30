package MIN.DosiNongBu.controller.admin.dto.request;

import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {

    private NoticeType noticeType;
    private String title;
    private String content;
    private List<String> imageUrls;

    @Builder
    public NoticeSaveRequestDto(NoticeType noticeType, String title, String content, List<String> imageUrls) {
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }

    public Notice toEntity(){
        if(imageUrls != null && imageUrls.size()>5){
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }

        return Notice.builder()
                .noticeType(noticeType)
                .title(title)
                .content(content)
                .imageUrls(imageUrls)
                .build();
    }
}

package MIN.DosiNongBu.controller.help.dto.response;

import MIN.DosiNongBu.domain.help.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeListResponseDto {

    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    public NoticeListResponseDto(Notice entity) {
        this.id = entity.getNoticeId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }
}

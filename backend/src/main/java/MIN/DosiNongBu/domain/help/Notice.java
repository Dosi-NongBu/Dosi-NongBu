package MIN.DosiNongBu.domain.help;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "NOTICES")
public class Notice extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    /* 속성 */
    @Enumerated(EnumType.STRING)
    @Column(name = "notice_type")
    private NoticeType noticeType;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @ElementCollection
    @CollectionTable(name = "FAQS_IMAGES", joinColumns = @JoinColumn(name = "faq_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Builder
    public Notice(NoticeType noticeType, String title, String content, List<String> imageUrls) {
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }

    //서비스 메서드
    public void update(NoticeType noticeType, String title, String content, List<String> imageUrls){
        this.noticeType = noticeType;
        this.title = title;
        this.content = content;

        if(imageUrls != null && imageUrls.size()>5){
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }
        else{
            this.imageUrls = imageUrls;
        }
    }
}

package MIN.DosiNongBu.domain.help;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "INQUIRYS")
public class Inquiry extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long inquiryId;

    /* FK */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /* 속성 */
    @Enumerated(EnumType.STRING)
    @Column(name = "inquiry_type")
    private InquiryType inquiryType;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @ElementCollection
    @CollectionTable(name = "INQUIRYS_IMAGES", joinColumns = @JoinColumn(name = "inquiry_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Column(name = "inquiry_status_type")
    private InquiryStatusType inquiryStatusType=InquiryStatusType.NO;

    @Lob
    @Column(name = "inquiry_answer")
    private String inquiryAnswer;

    @Builder
    public Inquiry(InquiryType inquiryType, String title, String content, List<String> imageUrls) {
        this.inquiryType = inquiryType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }

    // 서비스 메서드
    public void update(InquiryType inquiryType, String title, String content, List<String> imageUrls){
        this.inquiryType = inquiryType;
        this.title = title;
        this.content = content;
        this.imageUrls = imageUrls;
    }
}

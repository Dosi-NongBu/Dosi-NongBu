package MIN.DosiNongBu.domain.help;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.post.constant.PostType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "FAQS")
public class Faq extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id")
    private Long faqId;

    /* 속성 */
    @Enumerated(EnumType.STRING)
    @Column(name = "faq_type")
    private FaqType faqType;

    @Lob
    @Column(name = "faq_question")
    private String faqQuestion;

    @Lob
    @Column(name = "faq_answer")
    private String faqAnswer;

    @ElementCollection
    @CollectionTable(name = "FAQS_IMAGES", joinColumns = @JoinColumn(name = "faq_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Builder
    public Faq(FaqType faqType, String faqQuestion, String faqAnswer, List<String> imageUrls) {
        this.faqType = faqType;
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
        this.imageUrls = imageUrls;
    }
}

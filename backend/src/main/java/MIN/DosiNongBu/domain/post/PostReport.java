package MIN.DosiNongBu.domain.post;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "POSTS_REPRORTS")
public class PostReport extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_report_id")
    private Long postReportId;

    /* FK */
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    /* 속성 */
    @Column(name = "report_type_id")
    private ReportType reportType;

    @Builder
    public PostReport(ReportType reportType) {
        this.reportType = reportType;
    }

    public void setPost(Post post) {
        this.post = post;
        post.getPostReports().add(this);
    }
}

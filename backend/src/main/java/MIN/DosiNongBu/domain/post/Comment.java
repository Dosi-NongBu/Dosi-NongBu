package MIN.DosiNongBu.domain.post;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    /* FK */
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    /* 연관 */
    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentReport> commentReports = new ArrayList<CommentReport>();

    /* 속성 */
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "good")
    private Long good=0L;

    @Column(name = "bad")
    private Long bad=0L;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    // 서비스 메서드
    public void update(String content){
        this.content = content;
    }

    public void addReaction(ReactionType reactionType){
        if(reactionType == ReactionType.GOOD){
            this.good++;
        }
        else{
            this.bad++;
        }
    }

    public void updateReaction(ReactionType reactionType){
        if(reactionType == ReactionType.GOOD){
            this.bad--;
            this.good++;
        }
        else{
            this.good--;
            this.bad++;
        }
    }

}

package MIN.DosiNongBu.domain.post;

import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "POSTS_REACTIONS")
public class PostReaction {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_reaction_id")
    Long postReactionId;

    /* FK */
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    /* 속성 */
    @Column(name = "reaction_type")
    @Enumerated(EnumType.STRING)
    private ReactionType reaction;

    @Builder
    public PostReaction(ReactionType reaction) {
        this.reaction = reaction;
    }

    // 서비스 메소드
    public void update(ReactionType reaction){
        this.reaction = reaction;
    }
}

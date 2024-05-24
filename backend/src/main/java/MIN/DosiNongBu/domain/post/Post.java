package MIN.DosiNongBu.domain.post;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReactionType;
import MIN.DosiNongBu.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "POSTS")
public class Post extends BaseTimeEntity {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    /* FK */
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /* 연관 */
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostReport> postReports = new ArrayList<PostReport>();

    /* 속성 */
    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "good")
    private Long good = 0L;

    @Column(name = "bad")
    private Long bad = 0L;

    @ElementCollection
    @CollectionTable(name = "POSTS_IMAGES", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Builder
    public Post(String title, String content, PostType postType, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.imageUrls = imageUrls;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    // 서비스 메서드
    public void update(String title, String content, List<String> imageUrls){
        this.title = title;
        this.content = content;

        if (imageUrls.size() < 5) {
            this.imageUrls = imageUrls;
        } else {
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }
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

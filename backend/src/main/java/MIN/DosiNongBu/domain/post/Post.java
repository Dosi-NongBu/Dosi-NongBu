package MIN.DosiNongBu.domain.post;

import MIN.DosiNongBu.domain.BaseTimeEntity;
import MIN.DosiNongBu.domain.post.constant.PostType;
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

    /* 속성 */
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("0")
    @Column(name = "good")
    private Long good;

    @ColumnDefault("0")
    @Column(name = "bad")
    private Long bad;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType post;

    @ElementCollection
    @CollectionTable(name = "POSTS_IMAGES", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();


    @Builder
    public Post(String title, String content, Long good, Long bad, PostType post, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.good = good;
        this.bad = bad;
        this.post = post;
        this.imageUrls = imageUrls;
    }


    // 서비스 메서드
    public void addImageUrl(String imageUrl) {
        if (imageUrls.size() < 5) {
            imageUrls.add(imageUrl);
        } else {
            throw new IllegalStateException("이미지 URL은 최대 5개까지만 저장할 수 있습니다.");
        }
    }

}

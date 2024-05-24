package MIN.DosiNongBu.repository.post;

import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.PostReaction;
import MIN.DosiNongBu.domain.post.PostReport;
import MIN.DosiNongBu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends JpaRepository<PostReport, Long> {
    PostReport findByPostAndUser(Post post, User user);
}

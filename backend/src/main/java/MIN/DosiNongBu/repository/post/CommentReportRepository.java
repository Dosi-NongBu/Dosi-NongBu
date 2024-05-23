package MIN.DosiNongBu.repository.post;

import MIN.DosiNongBu.domain.post.Comment;
import MIN.DosiNongBu.domain.post.CommentReport;
import MIN.DosiNongBu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    CommentReport findByCommentAndUser(Comment comment, User user);
}

package MIN.DosiNongBu.repository.post;

import MIN.DosiNongBu.domain.post.Comment;
import MIN.DosiNongBu.domain.post.CommentReaction;
import MIN.DosiNongBu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    CommentReaction findByCommentAndUser(Comment comment, User user);
}

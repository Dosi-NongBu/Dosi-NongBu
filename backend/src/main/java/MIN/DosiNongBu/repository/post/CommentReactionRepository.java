package MIN.DosiNongBu.repository.post;

import MIN.DosiNongBu.domain.post.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
}

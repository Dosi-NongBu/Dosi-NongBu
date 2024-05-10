package MIN.DosiNongBu.repository.post;

import MIN.DosiNongBu.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByUser_UserId(Long userId, Pageable pageable);
}

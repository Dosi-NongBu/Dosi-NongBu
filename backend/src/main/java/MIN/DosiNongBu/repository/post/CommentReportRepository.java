package MIN.DosiNongBu.repository.post;

import MIN.DosiNongBu.domain.post.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {
}

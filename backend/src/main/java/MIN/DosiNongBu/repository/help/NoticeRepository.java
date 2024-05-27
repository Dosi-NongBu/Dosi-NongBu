package MIN.DosiNongBu.repository.help;

import MIN.DosiNongBu.domain.help.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

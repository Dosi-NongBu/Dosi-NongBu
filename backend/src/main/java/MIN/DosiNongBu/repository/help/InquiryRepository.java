package MIN.DosiNongBu.repository.help;

import MIN.DosiNongBu.domain.help.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}

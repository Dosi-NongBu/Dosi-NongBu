package MIN.DosiNongBu.repository.help;

import MIN.DosiNongBu.domain.help.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

}

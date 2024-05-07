package MIN.DosiNongBu.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCropRepository extends JpaRepository<UserCrop, Long> {
}

package MIN.DosiNongBu.repository.user;

import MIN.DosiNongBu.domain.user.UserCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCropRepository extends JpaRepository<UserCrop, Long> {
}

package MIN.DosiNongBu.repository.user;

import MIN.DosiNongBu.domain.user.UserCropAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCropAlarmRepository extends JpaRepository<UserCropAlarm,  Long> {
}

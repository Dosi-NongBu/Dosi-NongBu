package MIN.DosiNongBu.repository.user;

import MIN.DosiNongBu.domain.user.UserCropLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCropLogRepository extends JpaRepository<UserCropLog, Long> {

    Page<UserCropLog> findByUserCrop_UserCropId(Long userCropId, Pageable pageable);
}

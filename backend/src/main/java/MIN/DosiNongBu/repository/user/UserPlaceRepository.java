package MIN.DosiNongBu.repository.user;

import MIN.DosiNongBu.domain.user.UserPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPlaceRepository extends JpaRepository<UserPlace, Long> {

}

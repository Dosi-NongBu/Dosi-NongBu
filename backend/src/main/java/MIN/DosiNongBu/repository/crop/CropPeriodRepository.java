package MIN.DosiNongBu.repository.crop;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropPeriodRepository extends JpaRepository<CropPeriod, Long> {
}

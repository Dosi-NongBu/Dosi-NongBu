package MIN.DosiNongBu.repository.crop;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropInformationRepository extends JpaRepository<CropInformation, Long> {
}

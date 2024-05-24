package MIN.DosiNongBu.repository.crop;

import MIN.DosiNongBu.domain.crop.CropManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropManagementRepository extends JpaRepository<CropManagement, Long> {
}

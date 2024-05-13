package MIN.DosiNongBu.repository.crop;

import MIN.DosiNongBu.domain.crop.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    Page<Crop> findByNameContaining(String keyword, Pageable pageable);

    Page<Crop> findByMonth(Integer month, Pageable pageable);
}

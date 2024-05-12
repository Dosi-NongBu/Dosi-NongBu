package MIN.DosiNongBu.service.crop;

import MIN.DosiNongBu.controller.crop.dto.response.*;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.repository.crop.CropInformationRepository;
import MIN.DosiNongBu.repository.crop.CropManageRepository;
import MIN.DosiNongBu.repository.crop.CropPeriodRepository;
import MIN.DosiNongBu.repository.crop.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService{

    private final CropRepository cropRepository;
    private final CropInformationRepository cropInformationRepository;
    private final CropManageRepository cropManageRepository;
    private final CropPeriodRepository cropPeriodRepository;

    // 작물 목록 조회
    @Override
    public List<CropListResponseDto> viewCropList(Pageable pageable) {
        Page<Crop> entity = cropRepository.findAll(pageable);

        return entity.stream().map(CropListResponseDto::new).toList();
    }

    // 작물 추천 목록 조회
    @Override
    public List<CropListResponseDto> viewCropRecommendList(Pageable pageable) {
        int month = LocalDate.now().getMonthValue();
        Page<Crop> entity = cropRepository.findByMonth(month, pageable);

        return entity.stream().map(CropListResponseDto::new).toList();
    }

    // 작물 검색 (LIKE)
    @Override
    public List<CropListResponseDto> viewCropSearchList(String keyword, Pageable pageable) {
        Page<Crop> entity = cropRepository.findByNameContaining(keyword, pageable);

        return entity.stream().map(CropListResponseDto::new).toList();
    }

    // 작물 기본 정보
    @Override
    public CropMainResponseDto viewCropMain(Long cropId) {
        CropInformation entity = cropInformationRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));

        return null;
    }

    // 작물 상세 정보 및 관리법
    @Override
    public CropInfoResponseDto viewCropInfo(Long cropId) {
        return null;
    }

    // 작물 키우기
    @Override
    public CropManageResponseDto viewCropManage(Long cropId) {
        return null;
    }
}

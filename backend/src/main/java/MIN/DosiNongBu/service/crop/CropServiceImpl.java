package MIN.DosiNongBu.service.crop;

import MIN.DosiNongBu.controller.crop.dto.request.UserCropSaveRequestDto;
import MIN.DosiNongBu.controller.crop.dto.response.*;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManage;
import MIN.DosiNongBu.domain.crop.CropPeriod;
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

        if (entity == null || entity.isEmpty())
            throw new IllegalArgumentException("추천 작물이 없습니다.");

        return entity.stream().map(CropListResponseDto::new).toList();
    }

    // 작물 검색 (LIKE)
    @Override
    public List<CropListResponseDto> viewCropSearchList(String keyword, Pageable pageable) {
        Page<Crop> entity = cropRepository.findByNameContaining(keyword, pageable);

        if (entity == null || entity.isEmpty())
            throw new IllegalArgumentException("검색 작물이 없습니다.");

        return entity.stream().map(CropListResponseDto::new).toList();
    }

    // 작물 기본 정보
    @Override
    public CropMainResponseDto viewCropMain(Long cropId) {
        // 작물 기본 정보 조회
        Crop cropEntity = cropRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));

        // 작물 권장 관리 주기 조회
/*        List<CropPeriod> periodEntity = cropPeriodRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));*/

        return new CropMainResponseDto(cropEntity);
    }

    // 작물 상세 정보 및 관리법
    @Override
    public CropInfoResponseDto viewCropInfo(Long cropId) {
        // 작물 상세정보, 관리법은 같이 조회될 가능성이 높으므로 같이 조회
        // 작물 상세정보
        CropInformation infoEntity = cropInformationRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));

        // 작물 관리법
        CropManage manageEntity = cropManageRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));

        return new CropInfoResponseDto(infoEntity, manageEntity);
    }

    // 작물 키우기
    @Override
    public void registerUserCrop(UserCropSaveRequestDto requestDto) {


    }
}

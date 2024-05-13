package MIN.DosiNongBu.service.crop;

import MIN.DosiNongBu.controller.crop.dto.request.UserCropSaveRequestDto;
import MIN.DosiNongBu.controller.crop.dto.response.*;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManagement;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserPlace;
import MIN.DosiNongBu.repository.crop.CropInformationRepository;
import MIN.DosiNongBu.repository.crop.CropManageRepository;
import MIN.DosiNongBu.repository.crop.CropRepository;
import MIN.DosiNongBu.repository.user.UserCropRepository;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService{

    private final UserRepository userRepository;
    private final UserPlaceRepository userPlaceRepository;
    private final UserCropRepository userCropRepository;

    private final CropRepository cropRepository;
    private final CropInformationRepository cropInformationRepository;
    private final CropManageRepository cropManageRepository;

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

    // 작물 기본 정보, 권장 주기
    @Override
    public CropMainResponseDto viewCropMain(Long cropId) {
        // 작물 기본 정보 조회
        Crop cropEntity = cropRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));

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
        CropManagement manageEntity = cropManageRepository.findById(cropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + cropId));

        return new CropInfoResponseDto(infoEntity, manageEntity);
    }

    // 작물 키우기
    @Override
    public Long registerUserCrop(Long userId, UserCropSaveRequestDto requestDto) {
        UserCrop userCrop = requestDto.toEntity();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        UserPlace userPlace = userPlaceRepository.findById(requestDto.getUserPlaceId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 공간입니다. userPlaceId=" + requestDto.getUserPlaceId()));


        userCrop.setUser(user);
        userCrop.setUserPlace(userPlace);
        userCropRepository.save(userCrop);

        return userCrop.getUserCropId();
    }
}

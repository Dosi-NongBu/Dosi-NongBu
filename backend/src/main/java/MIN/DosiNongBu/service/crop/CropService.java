package MIN.DosiNongBu.service.crop;

import MIN.DosiNongBu.controller.crop.dto.request.UserCropSaveRequestDto;
import MIN.DosiNongBu.controller.crop.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CropService {

    // 작물 목록 조회
    List<CropListResponseDto> viewCropList(Pageable pageable);

    // 작물 추천 목록 조회
    List<CropListResponseDto> viewCropRecommendList(Pageable pageable);

    // 작물 검색 (LIKE)
    List<CropListResponseDto> viewCropSearchList(String keyword, Pageable pageable);

    // 작물 기본 정보
    CropMainResponseDto viewCropMain(Long cropId);

    // 작물 상세 정보 및 관리법
    CropInfoResponseDto viewCropInfo(Long cropId);

    // 작물 키우기
    Long registerUserCrop(Long userId, UserCropSaveRequestDto requestDto);

}

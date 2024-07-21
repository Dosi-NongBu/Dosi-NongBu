package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.UserCropAlarmUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropAlarmResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropLogListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCropManageService {

    //내 작물 목록 조회
    List<UserCropListResponseDto> findUserCropList(Long userId);

    //내 작물 조회
    UserCropResponseDto viewUserCrop(Long userCropId);

    //내 작물 알림 조회
    UserCropAlarmResponseDto viewUserCropAlarm(Long userCropId);

    //내 작물 알림 수정
    Long updateUserCropAlarm(Long userCropId, UserCropAlarmUpdateRequestDto requestDto);

    //내 작물 관리 목로 조회
    List<UserCropLogListResponseDto> viewUserCropLogList(Long userCropId, Pageable pageable);

    //내 작물 관리 추가
    Long registerUserCropLog(Long userCropId, String manageType);

    //내 작물 관리 삭제
    Long deleteUserCropLog(Long cropLogId);

    //내 작물 사진 추가
    Long updateUserCropImage(Long userCropId, UserCropImageSaveRequestDto requestDto);
}

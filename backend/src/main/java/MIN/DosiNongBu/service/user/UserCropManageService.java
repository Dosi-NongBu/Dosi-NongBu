package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.UserCropAlarmUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropManageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropAlarmResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropManageListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;

import java.util.List;

public interface UserCropManageService {

    //내 작물 목록 조회
    List<UserCropListResponseDto> findUserCropList(Long userId);

    //내 작물 조회
    UserCropResponseDto viewUserCrop(Long userCropId);

    //내 작물 알림 조회
    UserCropAlarmResponseDto viewUserCropAlarm();

    //내 작물 알림 수정
    UserCropAlarmUpdateRequestDto updateUserCropAlarm();

    //내 작물 관리 목로 조회
    List<UserCropManageListResponseDto> viewUserCropManageList();

    //내 작물 관리 추가
    UserCropManageSaveRequestDto registerUserCropManage();

    //내 작물 관리 삭제
    void deleteUserCropManage();

    //내 작물 사진 추가
    UserCropImageSaveRequestDto registerUserCropImage();

    //내 작물 사진 수정
    UserCropImageUpdateRequestDto updateUserCropImage();
}

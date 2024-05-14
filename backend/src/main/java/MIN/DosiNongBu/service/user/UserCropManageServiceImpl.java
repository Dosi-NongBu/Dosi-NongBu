package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.UserCropAlarmUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropManageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropAlarmResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropManageListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserCropAlarm;
import MIN.DosiNongBu.domain.user.UserCropLog;
import MIN.DosiNongBu.repository.user.UserCropAlarmRepository;
import MIN.DosiNongBu.repository.user.UserCropLogRepository;
import MIN.DosiNongBu.repository.user.UserCropRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserCropManageServiceImpl implements UserCropManageService{

    private final UserRepository userRepository;
    private final UserCropRepository userCropRepository;
    private final UserCropAlarmRepository userCropAlarmRepository;
    private final UserCropLogRepository userCropLogRepository;

    // 내 작물 목록 조회
    @Override
    public List<UserCropListResponseDto> findUserCropList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        List<UserCrop> entity = user.getUserCrops();
        return entity.stream().map(UserCropListResponseDto::new).toList();
    }

    // 내 작물 조회
    @Override
    public UserCropResponseDto viewUserCrop(Long userCropId) {
        UserCrop entity = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        return new UserCropResponseDto(entity);
    }

    // 내 작물 알림 조회
    @Override
    public UserCropAlarmResponseDto viewUserCropAlarm(Long userCropId) {
        UserCrop entity = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        return new UserCropAlarmResponseDto(entity);
    }

    // 내 작물 알림 수정
    @Override
    public void updateUserCropAlarm(Long userCropId, UserCropAlarmUpdateRequestDto requestDto) {
        UserCrop userCrop = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        List<UserCropAlarm> userCropAlarms = userCrop.getUserCropAlarms();

        for(UserCropAlarm userCropAlarm : userCropAlarms){
            if (userCropAlarm.getManage() == CropManageType.WATER) {
                userCropAlarm.update(requestDto.getIsWaterAlarm(), requestDto.getWater());
                continue;
            } else if (userCropAlarm.getManage() == CropManageType.VENTILATION) {
                userCropAlarm.update(requestDto.getIsVentilationAlarm(), requestDto.getVentilation());
                continue;
            } else if (userCropAlarm.getManage() == CropManageType.REPOT) {
                userCropAlarm.update(requestDto.getIsRepotAlarm(), requestDto.getRepot());
                continue;
            } else if (userCropAlarm.getManage() == CropManageType.PRUNING) {
                userCropAlarm.update(requestDto.getIsPruningAlarm(), requestDto.getPruning());
            }
        }
    }

    // 내 작물 관리 목록 조회
    @Override
    public List<UserCropManageListResponseDto> viewUserCropManageList(Long userCropId) {
        UserCrop entity = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        List<UserCropLog> userCropLogs = entity.getUserCropLogs();

        return userCropLogs.stream().map(UserCropManageListResponseDto::new).toList();
    }

    // 내 작물 관리 추가
    @Override
    public void registerUserCropManage(Long userCropId, UserCropManageSaveRequestDto requestDto) {
        UserCrop userCrop = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        UserCropLog userCropLog = requestDto.toEntity();

        userCropLog.setUserCrop(userCrop);
        userCropLogRepository.save(userCropLog);
    }

    // 내 작물 관리 삭제
    @Override
    public void deleteUserCropManage() {

    }

    // 내 작물 사진 추가
    @Override
    public UserCropImageSaveRequestDto registerUserCropImage() {
        return null;
    }

    // 내 작물 사진 수정
    @Override
    public UserCropImageUpdateRequestDto updateUserCropImage() {
        return null;
    }
}

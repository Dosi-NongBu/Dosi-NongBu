package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.UserCropAlarmUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropLogSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropAlarmResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropLogListResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Long updateUserCropAlarm(Long userCropId, UserCropAlarmUpdateRequestDto requestDto) {
        UserCrop userCrop = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        List<UserCropAlarm> userCropAlarms = userCrop.getUserCropAlarms();

        for(UserCropAlarm userCropAlarm : userCropAlarms){
            if (userCropAlarm.getManageType() == CropManageType.WATER) {
                userCropAlarm.update(requestDto.getIsWaterAlarm(), requestDto.getWater());
                continue;
            } else if (userCropAlarm.getManageType() == CropManageType.VENTILATION) {
                userCropAlarm.update(requestDto.getIsVentilationAlarm(), requestDto.getVentilation());
                continue;
            } else if (userCropAlarm.getManageType() == CropManageType.REPOT) {
                userCropAlarm.update(requestDto.getIsRepotAlarm(), requestDto.getRepot());
                continue;
            } else if (userCropAlarm.getManageType() == CropManageType.PRUNING) {
                userCropAlarm.update(requestDto.getIsPruningAlarm(), requestDto.getPruning());
            }
        }

        return userCropId;
    }

    // 내 작물 관리 목록 조회
    @Override
    public List<UserCropLogListResponseDto> viewUserCropLogList(Long userCropId, Pageable pageable) {
        Page<UserCropLog> entity = userCropLogRepository.findByUserCrop_UserCropId(userCropId, pageable);

        return entity.stream().map(UserCropLogListResponseDto::new).toList();
    }

    // 내 작물 관리 추가
    @Override
    @Transactional
    public Long registerUserCropLog(Long userCropId, UserCropLogSaveRequestDto requestDto) {
        UserCrop userCrop = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        UserCropLog userCropLog = requestDto.toEntity();

        userCropLog.setUserCrop(userCrop);
        userCropLogRepository.save(userCropLog);

        return userCropLog.getCropLogId();
    }

    // 내 작물 관리 삭제
    @Override
    @Transactional
    public Long deleteUserCropLog(Long cropLogId) {
        userCropLogRepository.deleteById(cropLogId);

        return cropLogId;
    }

    // 내 작물 사진 추가
    @Override
    @Transactional
    public Long updateUserCropImage(Long userCropId, UserCropImageSaveRequestDto requestDto) {
        UserCrop userCrop = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));

        List<String> newImageUrls = requestDto.getImageUrls();
        List<String> imageUrls = userCrop.getImageUrls();

        int minSize = Math.min(newImageUrls.size(), imageUrls.size());

        // 기존 이미지들과 다르면 사진 수정
        for(int i=0;i<minSize; i++){
            if(imageUrls.get(i).equals(newImageUrls.get(i))){
                continue;
            }
            imageUrls.set(i, newImageUrls.get(i));
        }

        // 기존 이미지들에 없었던 사진 추가
        for(int i=imageUrls.size();i < newImageUrls.size();i++){
            userCrop.addImageUrl(newImageUrls.get(i));
        }

        // 기존 이미지들이 없다면 사진 삭제
        if (imageUrls.size() > newImageUrls.size()) {
            imageUrls.subList(newImageUrls.size(), imageUrls.size()).clear();
        }

        // 변경된 이미지 리스트를 저장
        userCrop.setImageUrls(imageUrls);
        userCropRepository.save(userCrop);

        return userCropId;
    }

}

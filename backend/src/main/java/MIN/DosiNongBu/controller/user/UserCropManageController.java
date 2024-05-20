package MIN.DosiNongBu.controller.user;

import MIN.DosiNongBu.controller.user.dto.request.UserCropAlarmUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropLogSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropAlarmResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropLogListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;
import MIN.DosiNongBu.service.user.UserCropManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserCropManageController {

    private final UserCropManageService userCropManageService;

    public Long UserCookie(String cookie) {
        Long userId = null;
        try {
            if (cookie != null) {
                userId = Long.parseLong(cookie);
            }
        } catch (NumberFormatException e) {
            //변환 할 수 없을 때
            throw new IllegalArgumentException("잘못된 유저 ID 입니다");
        }
        return userId;
    }

    //내 작물 목록 조회
    @GetMapping("/usercrops")
    public List<UserCropListResponseDto> userCropList(@CookieValue(name = "User") String cookie) {
        Long userId = UserCookie(cookie);

        return userCropManageService.findUserCropList(userId);
    }

    //내 작물 조회
    @GetMapping("/usercrops/{userCropId}")
    public UserCropResponseDto userCrop(@PathVariable Long userCropId) {

        return userCropManageService.viewUserCrop(userCropId);
    }

    // 내 작물 알림 조회
    @GetMapping("/alarms/{userCropId}")
    public UserCropAlarmResponseDto userCropAlarm(@PathVariable Long userCropId) {

        return userCropManageService.viewUserCropAlarm(userCropId);
    }

    // 내 작물 알림 수정
    @PutMapping("/alarms/{userCropId}")
    public void UpdateUserCropAlarm(@PathVariable Long userCropId, UserCropAlarmUpdateRequestDto requestDto) {

        userCropManageService.updateUserCropAlarm(userCropId, requestDto);
    }

    // 내 작물 관리 타임라인 조회
    @GetMapping("/manages/{userCropId}")
    public List<UserCropLogListResponseDto>  userCropManageTimeLine(@PathVariable Long userCropId, Pageable pageable) {

        return userCropManageService.viewUserCropLogList(userCropId, pageable);
    }

    // 내 작물 관리 추가
    @PostMapping("/manages/{userCropId}")
    public void createUserCropManage(@PathVariable Long userCropId, UserCropLogSaveRequestDto requestDto) {

        userCropManageService.registerUserCropLog(userCropId, requestDto);
    }

    // 내 작물 관리 삭제
    @DeleteMapping("/manages/{userCropId}/{cropLogId}")
    public void deleteUserCropLog(@PathVariable Long userCropId, @PathVariable Long cropLogId) {

        userCropManageService.deleteUserCropLog(cropLogId);
    }

    // 내 작물 사진 추가
    @PostMapping("/images/{userCropId}")
    public void createUserCropImage(@PathVariable Long userCropId, UserCropImageSaveRequestDto requestDto) {

        userCropManageService.updateUserCropImage(userCropId, requestDto);
    }

}

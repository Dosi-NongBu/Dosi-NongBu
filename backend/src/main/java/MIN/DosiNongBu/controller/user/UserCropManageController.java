package MIN.DosiNongBu.controller.user;

import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
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
    public void userCrop(@PathVariable Long userCropId, Model model) {
        UserCropResponseDto responseDto = userCropManageService.viewUserCrop(userCropId);

        model.addAttribute("userCrop", responseDto);
    }

    // 내 작물 알림 조회
    @GetMapping("/alarms/{userCropId}")
    public void userCropAlarm(@PathVariable Long userCropId) {

    }

    // 내 작물 알림 수정
    @PutMapping("/alarms/{userCropId}")
    public void UpdateUserCropAlarm(@PathVariable Long userCropId) {

    }

    // 내 작물 관리 타임라인 조회
    @GetMapping("/manages/{userCropId}")
    public void userCropManageTimeLine(@PathVariable Long userCropId, Pageable pageable) {

    }

    // 내 작물 관리 추가
    @PostMapping("/manages/{userCropId}")
    public void createUserCropManage(@PathVariable Long userCropId) {

    }

    // 내 작물 관리 목록 삭제
    @DeleteMapping("/manages/{userCropId}/{cropLogId}")
    public void deleteUserCropLog(@PathVariable Long userCropId, @PathVariable Long cropLogId) {

    }

    // 내 작물 사진 추가
    @PostMapping("/images/{userCropId}")
    public void createUserCropImage(@PathVariable Long userCropId) {

    }

    // 내 작물 사진 삭제
    @DeleteMapping("/images/{userCropId}")
    public void deleteUserCropImage(@PathVariable Long userCropId) {

    }
}

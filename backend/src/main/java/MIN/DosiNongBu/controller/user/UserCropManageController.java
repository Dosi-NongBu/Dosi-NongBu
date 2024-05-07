package MIN.DosiNongBu.controller.user;

import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
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

    public Long UserCookie(String cookie){
        Long userId = null;
        try {
            if(cookie != null){
                userId = Long.parseLong(cookie);
            }
        } catch (NumberFormatException e){
            //변환 할 수 없을 때
            throw new IllegalArgumentException("잘못된 유저 ID 입니다");
        }
        return userId;
    }

    //내 작물 목록 조회
    @GetMapping("/usercrops")
    public List<UserCropListResponseDto> userCropList(@CookieValue(name = "User") String cookie){
        Long userId = UserCookie(cookie);

        return userCropManageService.findUserCropList(userId);
    }

    //내 작물 조회
    @GetMapping("/usercrops/{userCropId}")
    public void userCrop(@PathVariable Long userCropId, Model model){
        userCropManageService.viewUserCrop(userCropId);

        model.addAttribute("userCrop");
    }

    // 내 작물 알림 조회

    // 내 작물 알림 수정

    // 내 작물 관리 타임라인 조회

    // 내 작물 관리 추가

    // 내 작물 관리 목록 삭제

    // 내 작물 사진 추가

    // 내 작물 사진 삭제


}

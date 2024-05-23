package MIN.DosiNongBu.controller.crop;

import MIN.DosiNongBu.controller.crop.dto.request.UserCropSaveRequestDto;
import MIN.DosiNongBu.controller.crop.dto.response.CropInfoResponseDto;
import MIN.DosiNongBu.controller.crop.dto.response.CropListResponseDto;
import MIN.DosiNongBu.controller.crop.dto.response.CropMainResponseDto;
import MIN.DosiNongBu.service.crop.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CropController {

    private final CropService cropService;

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

    // 작물 목록 조회
    @GetMapping("/crops")
    public List<CropListResponseDto> cropList(Pageable pageable){

        return cropService.viewCropList(pageable);
    }

    // 작물 추천 목록 조회
    @GetMapping("/crops/recommend")
    public List<CropListResponseDto> cropListToRecommend(Pageable pageable){

        return cropService.viewCropRecommendList(pageable);
    }

    // 작물 검색 (LIKE)
    @GetMapping("/crops/{keyword}")
    public List<CropListResponseDto> cropListToSearch(@PathVariable String keyword, Pageable pageable){

        return cropService.viewCropRecommendList(pageable);
    }

    // 작물 메인 페이지, 기본정보
    @GetMapping("/crops/{cropId}/main")
    public CropMainResponseDto cropMainPage(@PathVariable Long cropId){

        return cropService.viewCropMain(cropId);
    }

    // 작물 상세 정보 및 관리법
    @GetMapping("crops/{cropId}/info")
    public CropInfoResponseDto cropInfo(@PathVariable Long cropId){

        return cropService.viewCropInfo(cropId);
    }

    // 작물 키우기
    @PostMapping("/crops/{cropId}/grow")
    public Long cropGrow(@CookieValue(name = "User") String cookie, @PathVariable Long cropId, @RequestBody UserCropSaveRequestDto requestDto){
        Long userId = UserCookie(cookie);

        return cropService.registerUserCrop(userId, requestDto);
    }


}

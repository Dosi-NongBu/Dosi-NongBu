package MIN.DosiNongBu.controller.crop;

import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("api/v1")
public class CropController {

    // 작물 목록 조회
    @GetMapping("/crops")
    public void cropList(Pageable pageable){

    }

    // 작물 추천 목록 조회
    @GetMapping("/crops/recommend")
    public void cropListToRecommend(){

    }

    // 작물 검색 (LIKE)
    @GetMapping("/crops/{keyword}")
    public void cropListToSearch(@PathVariable String keyword){

    }

    // 작물 기본 정보
    @GetMapping("/crops/{cropId}/main")
    public void cropMainPage(@PathVariable Long cropId){

    }

    // 작물 상세 정보 및 관리법
    @GetMapping("crops/{cropId}/info")
    public void cropInfo(@PathVariable Long cropId){

    }

    // 작물 키우기
    @PostMapping("/crops/{cropId}/grow")
    public void cropGrow(@PathVariable Long cropId){

    }


}

package MIN.DosiNongBu.controller.admin;

import MIN.DosiNongBu.controller.admin.dto.request.NewCropRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    // 신규 작물 등록
    @PostMapping("/admins/crops/add")
    public void createNewCrop(NewCropRequestDto requestDto){

    }

    // 사용자 정보 목록 조회

    // 사용자 정보 조회

    // 사용자 정보 추가

    // 사용자 정보 수정

    // 1:1 문의 답변 등록

    // 1:1 문의 답변 수정

    // 공지사항 등록

    // 공지사항 수정

    // FAQ 등록

    // FAQ 수정
}
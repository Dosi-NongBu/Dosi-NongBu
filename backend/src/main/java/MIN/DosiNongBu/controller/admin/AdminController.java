package MIN.DosiNongBu.controller.admin;

import MIN.DosiNongBu.controller.admin.dto.request.NewCropRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    // 신규 작물 등록
    @PostMapping("/admins/crops/add")
    public void createNewCrop(NewCropRequestDto requestDto){

    }

    // 사용자 정보 목록 조회
    @GetMapping("/admins/users")
    public void userList(){

    }

    // 사용자 정보 조회
    @GetMapping("/admins/users/{userId]")
    public void user(@PathVariable Long userId){

    }

    // 사용자 정보 추가
/*    @PostMapping("/admins/users")
    public void createUser(){

    }*/

    // 사용자 정보 수정
    @PutMapping("/admins/users/{userId}")
    public void updateUser(){

    }

    // 1:1 문의 답변 등록
    @PutMapping("/admins/inquiries/{inquiryId}")
    public void createInquiry(){

    }

    // 1:1 문의 답변 수정
/*    @PutMapping("/admins/inquiries/{inquiryId}")
    public void updateInquiry(){

    }*/

    // 공지사항 등록
    @PostMapping("/admins/notices")
    public void createNotice(){

    }

    // 공지사항 수정
    @PutMapping("/admins/notices/{noticeId}")
    public void updateNotice(){

    }

    // FAQ 등록
    @PostMapping("/admins/faqs")
    public void createFaq(){

    }

    // FAQ 수정
    @PutMapping("/admins/faqs/{faqId}")
    public void updateFaq(){


    }
}

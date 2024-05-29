package MIN.DosiNongBu.controller.admin;

import MIN.DosiNongBu.controller.admin.dto.request.*;
import MIN.DosiNongBu.controller.admin.dto.response.UserListResponseDto;
import MIN.DosiNongBu.controller.admin.dto.response.UserResponseDto;
import MIN.DosiNongBu.service.admin.AdminService;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminController {

    private final AdminService adminService;

    // 신규 작물 등록
    @PostMapping("/admins/crops/add")
    public Long createNewCrop(NewCropRequestDto requestDto){
        return adminService.registerNewCrop(requestDto);
    }

    // 사용자 정보 목록 조회
    @GetMapping("/admins/users")
    public List<UserListResponseDto> userList(Pageable pageable){
        return adminService.viewUserList(pageable);
    }

    // 사용자 정보 조회
    @GetMapping("/admins/users/{userId}")
    public UserResponseDto user(@PathVariable Long userId){
        return adminService.viewUser(userId);
    }

    // 사용자 정보 추가
/*    @PostMapping("/admins/users")
    public void createUser(){

    }*/

    // 사용자 정보 수정
    @PutMapping("/admins/users/{userId}")
    public Long updateUser(@PathVariable Long userId, UserUpdateRequestDto requestDto){
        return adminService.updateUser(userId, requestDto);
    }

    // 1:1 문의 답변 등록
    @PutMapping("/admins/inquiries/{inquiryId}")
    public Long createInquiry(@PathVariable Long inquiryId, InquiryAnswerRequestDto requestDto){
        return adminService.registerInquiryAnswer(inquiryId, requestDto);
    }

    // 1:1 문의 답변 수정
/*    @PutMapping("/admins/inquiries/{inquiryId}")
    public void updateInquiry(){

    }*/

    // 공지사항 등록
    @PostMapping("/admins/notices")
    public Long createNotice(NoticeSaveRequestDto requestDto){
        return adminService.registerNotice(requestDto);
    }

    // 공지사항 수정
    @PutMapping("/admins/notices/{noticeId}")
    public Long updateNotice(@PathVariable Long noticeId, NoticeUpdateRequestDto requestDto){
        return adminService.updateNotice(noticeId, requestDto);
    }

    // FAQ 등록
    @PostMapping("/admins/faqs")
    public Long createFaq(FaqSaveRequestDto requestDto){
        return adminService.registerFAQ(requestDto);
    }

    // FAQ 수정
    @PutMapping("/admins/faqs/{faqId}")
    public Long updateFaq(@PathVariable Long faqId, FaqUpdateRequestDto requestDto) {
        return adminService.updateFAQ(faqId, requestDto);
    }
}

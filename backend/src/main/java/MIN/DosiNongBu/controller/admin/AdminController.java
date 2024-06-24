package MIN.DosiNongBu.controller.admin;

import MIN.DosiNongBu.controller.admin.dto.request.*;
import MIN.DosiNongBu.controller.admin.dto.response.UserListResponseDto;
import MIN.DosiNongBu.controller.admin.dto.response.UserResponseDto;
import MIN.DosiNongBu.service.admin.AdminService;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminController {

    private final AdminService adminService;

    // 신규 작물 등록
    @PostMapping("/admins/crops/add")
    public Long createNewCrop(@RequestBody NewCropRequestDto requestDto){
        log.info("신규 작물 등록");
        return adminService.registerNewCrop(requestDto);
    }

    // 사용자 정보 목록 조회
    @GetMapping("/admins/users")
    public List<UserListResponseDto> userList(Pageable pageable){
        log.info("사용자 정보 목록 조회");
        return adminService.viewUserList(pageable);
    }

    // 사용자 정보 조회
    @GetMapping("/admins/users/{userId}")
    public UserResponseDto user(@PathVariable Long userId){
        log.info("사용자 정보 조회");
        return adminService.viewUser(userId);
    }

    // 사용자 정보 추가
/*    @PostMapping("/admins/users")
    public void createUser(){

    }*/

    // 사용자 정보 수정
    @PutMapping("/admins/users/{userId}")
    public Long updateUser(@PathVariable Long userId,
                           @RequestBody UserUpdateRequestDto requestDto){
        log.info("사용자 정보 수정");
        return adminService.updateUser(userId, requestDto);
    }

    // 1:1 문의 답변 등록
    @PutMapping("/admins/inquiries/{inquiryId}")
    public Long createInquiry(@PathVariable Long inquiryId,
                              @RequestBody InquiryAnswerRequestDto requestDto){
        log.info("1:1 문의 답변 등록");
        return adminService.registerInquiryAnswer(inquiryId, requestDto);
    }

    // 1:1 문의 답변 수정
/*    @PutMapping("/admins/inquiries/{inquiryId}")
    public void updateInquiry(){

    }*/

    // 공지사항 등록
    @PostMapping("/admins/notices")
    public Long createNotice(@RequestBody NoticeSaveRequestDto requestDto){
        log.info("공지사항 등록");
        return adminService.registerNotice(requestDto);
    }

    // 공지사항 수정
    @PutMapping("/admins/notices/{noticeId}")
    public Long updateNotice(@PathVariable Long noticeId,
                             @RequestBody NoticeUpdateRequestDto requestDto){
        log.info("공지 사항 수정");
        return adminService.updateNotice(noticeId, requestDto);
    }

    // FAQ 등록
    @PostMapping("/admins/faqs")
    public Long createFaq(@RequestBody FaqSaveRequestDto requestDto){
        log.info("FAQ 등록");
        return adminService.registerFAQ(requestDto);
    }

    // FAQ 수정
    @PutMapping("/admins/faqs/{faqId}")
    public Long updateFaq(@PathVariable Long faqId,
                          @RequestBody FaqUpdateRequestDto requestDto) {
        log.info("FAQ 수정");
        return adminService.updateFAQ(faqId, requestDto);
    }
}

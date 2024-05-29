package MIN.DosiNongBu.service.admin;

import MIN.DosiNongBu.controller.admin.dto.request.*;
import MIN.DosiNongBu.controller.admin.dto.response.UserListResponseDto;
import MIN.DosiNongBu.controller.admin.dto.response.UserResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    // 신규 작물 등록
    Long registerNewCrop(NewCropRequestDto requestDto);
    // 사용자 정보 목록 조회
    List<UserListResponseDto> viewUserList(Pageable pageable);
    // 사용자 정보 조히
    UserResponseDto viewUser(Long userId);
    // 사용자 정보 추가
    void registerUser();
    // 사용자 정보 수정
    Long updateUser(Long userId, UserUpdateRequestDto requestDto);
    // 1:1 문의 답변 등록
    Long registerInquiryAnswer(Long inquiryId, InquiryAnswerRequestDto requestDto);
    // 1:1 문의 답변 수정
    void updateInquiryAnswer();
    // 공지사항 등록
    Long registerNotice(NoticeSaveRequestDto requestDto);
    // 공지사항 수정
    Long updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto);
    // FAQ 등록
    Long registerFAQ(FaqSaveRequestDto requestDto);
    // FAQ 수정
    Long updateFAQ(Long faqId, FaqUpdateRequestDto requestDto);

}

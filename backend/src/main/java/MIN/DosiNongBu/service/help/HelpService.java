package MIN.DosiNongBu.service.help;

import MIN.DosiNongBu.controller.help.dto.request.InquirySaveRequestDto;
import MIN.DosiNongBu.controller.help.dto.request.InquiryUpdateRequestDto;
import MIN.DosiNongBu.controller.help.dto.response.*;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HelpService {

    // 1:1 문의 목록 조회
    List<InquiryListResponseDto> viewInquiryList(Pageable pageable);

    // 1:1 문의 조회
    InquiryResponseDto viewInquiry(Long inquiryId);

    // 1:1 문의 작성
    Long registerInquiry(Long userId, InquiryType inquiryType, InquirySaveRequestDto requestDto);

    // 1:1 문의 수정
    Long updateInquiry(Long inquiryId, InquiryUpdateRequestDto requestDto);

    // 1:1 문의 삭제
    Long deleteInquiry(Long inquiryId);

    // 공지사항 목록 조회
    List<NoticeListResponseDto> viewNoticeList(Pageable pageable);

    // 공지사항 조회
    NoticeResponseDto viewNotice(Long noticeId);

    // FAQ 목록 조회
    List<FaqListResponseDto> viewFaqList(Pageable pageable);

    // FAQ 조회
    FaqResponseDto viewFaq(Long faqId);
}

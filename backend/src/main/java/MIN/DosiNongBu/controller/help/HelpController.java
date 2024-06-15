package MIN.DosiNongBu.controller.help;

import MIN.DosiNongBu.controller.help.dto.request.InquirySaveRequestDto;
import MIN.DosiNongBu.controller.help.dto.request.InquiryUpdateRequestDto;
import MIN.DosiNongBu.controller.help.dto.response.*;
import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.service.help.HelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HelpController {

    private final HelpService helpService;

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

    @GetMapping("/inquiries")
    public List<InquiryListResponseDto> inquiryList(Pageable pageable){
        log.info("문의 목록 조회");
        return helpService.viewInquiryList(pageable);
    }

    @GetMapping("/inquiries/{inquiryId}")
    public InquiryResponseDto inquiry(@PathVariable Long inquiryId){
        log.info("문의 조회");
        return helpService.viewInquiry(inquiryId);
    }

    @PostMapping("/inquiries")
    public Long createInquiry(@CookieValue(name = "User") String cookie, @RequestParam("InquiryType") String inquiryType, @RequestBody InquirySaveRequestDto requestDto){
        log.info("문의 등록");
        Long userId = UserCookie(cookie);

        return helpService.registerInquiry(userId, inquiryType, requestDto);
    }

    @PutMapping("/inquiries/{inquiryId}")
    public Long updateInquiry(@PathVariable Long inquiryId, @RequestBody InquiryUpdateRequestDto requestDto){
        log.info("문의 수정");
        return helpService.updateInquiry(inquiryId, requestDto);
    }

    @DeleteMapping("/inquiries/{inquiryId}")
    public Long deleteInquiry(@PathVariable Long inquiryId){
        log.info("문의 삭제");
        return helpService.deleteInquiry(inquiryId);
    }

    @GetMapping("/notices")
    public List<NoticeListResponseDto> noticeList(Pageable pageable){
        log.info("공지사항 목록 조회");
        return helpService.viewNoticeList(pageable);
    }

    @GetMapping("/notices/{noticeId}")
    public NoticeResponseDto notice(@PathVariable Long noticeId){
        log.info("공지사항 조회");
        return helpService.viewNotice(noticeId);
    }

    @GetMapping("/faqs")
    public List<FaqListResponseDto> faqList(Pageable pageable){
        log.info("FAQ 목록 조회");
        return helpService.viewFaqList(pageable);
    }

    @GetMapping("/faqs/{faqId}")
    public FaqResponseDto faq(@PathVariable Long faqId){
        log.info("FAQ 조회");
        return helpService.viewFaq(faqId);
    }

}

package MIN.DosiNongBu.controller.help;

import MIN.DosiNongBu.controller.help.dto.request.InquirySaveRequestDto;
import MIN.DosiNongBu.controller.help.dto.request.InquiryUpdateRequestDto;
import MIN.DosiNongBu.controller.help.dto.response.*;
import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.service.help.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return helpService.viewInquiryList(pageable);
    }

    @GetMapping("/inquiries/{inquiryId}")
    public InquiryResponseDto inquiry(@PathVariable Long inquiryId){

        return helpService.viewInquiry(inquiryId);
    }

    @PostMapping("/inquiries")
    public Long createInquiry(@CookieValue(name = "User") String cookie, @RequestParam InquiryType inquiryType, InquirySaveRequestDto requestDto){
        Long userId = UserCookie(cookie);

        return helpService.registerInquiry(userId, inquiryType, requestDto);
    }

    @PutMapping("/inquiries/{inquiryId}")
    public Long updateInquiry(@PathVariable Long inquiryId, InquiryUpdateRequestDto requestDto){

        return helpService.updateInquiry(inquiryId, requestDto);
    }

    @DeleteMapping("/inquiries/{inquiryId}")
    public Long deleteInquiry(@PathVariable Long inquiryId){

        return helpService.deleteInquiry(inquiryId);
    }

    @GetMapping("/notices")
    public List<NoticeListResponseDto> noticeList(Pageable pageable){

        return helpService.viewNoticeList(pageable);
    }

    @GetMapping("/notices/{noticeId}")
    public NoticeResponseDto notice(@PathVariable Long noticeId){

        return helpService.viewNotice(noticeId);
    }

    @GetMapping("/faqs")
    public List<FaqListResponseDto> faqList(Pageable pageable){

        return helpService.viewFaqList(pageable);
    }

    @GetMapping("/faqs/{faqId}")
    public FaqResponseDto faq(@PathVariable Long faqId){

        return helpService.viewFaq(faqId);
    }

}

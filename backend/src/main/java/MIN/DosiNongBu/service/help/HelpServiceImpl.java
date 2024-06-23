package MIN.DosiNongBu.service.help;

import MIN.DosiNongBu.controller.help.dto.request.InquirySaveRequestDto;
import MIN.DosiNongBu.controller.help.dto.request.InquiryUpdateRequestDto;
import MIN.DosiNongBu.controller.help.dto.response.*;
import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.repository.help.FaqRepository;
import MIN.DosiNongBu.repository.help.InquiryRepository;
import MIN.DosiNongBu.repository.help.NoticeRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService{

    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private final NoticeRepository noticeRepository;
    private final FaqRepository faqRepository;

    @Override
    public List<InquiryListResponseDto> viewInquiryList(Pageable pageable) {
        Page<Inquiry> entity = inquiryRepository.findAll(pageable);

        return entity.stream().map(InquiryListResponseDto::new).toList();
    }

    @Override
    public InquiryResponseDto viewInquiry(Long inquiryId) {
        Inquiry entity = inquiryRepository.findById(inquiryId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 문의입니다. inquiryId=" + inquiryId));

        return new InquiryResponseDto(entity);
    }

    @Override
    public Long registerInquiry(Long userId, String inquiryType, InquirySaveRequestDto requestDto) {
        Inquiry entity = requestDto.toEntity(inquiryType);

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        entity.setUser(user);
        inquiryRepository.save(entity);

        return entity.getInquiryId();
    }

    @Override
    public Long updateInquiry(Long inquiryId, InquiryUpdateRequestDto requestDto) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 문의입니다. inquiryId=" + inquiryId));

        inquiry.update(requestDto.getInquiryType(), requestDto.getTitle(), requestDto.getContent(), requestDto.getImageUrls());

        return inquiryId;
    }

    @Override
    public Long deleteInquiry(Long inquiryId) {
        inquiryRepository.deleteById(inquiryId);

        return inquiryId;
    }

    @Override
    public List<NoticeListResponseDto> viewNoticeList(Pageable pageable) {
        Page<Notice> entity = noticeRepository.findAll(pageable);

        return entity.stream().map(NoticeListResponseDto::new).toList();
    }

    @Override
    public NoticeResponseDto viewNotice(Long noticeId) {
        Notice entity = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 공지사항입니다. noticeId=" + noticeId));

        return new NoticeResponseDto(entity);
    }

    @Override
    public List<FaqListResponseDto> viewFaqList(Pageable pageable) {
        Page<Faq> entity = faqRepository.findAll(pageable);

        return entity.stream().map(FaqListResponseDto::new).toList();
    }

    @Override
    public FaqResponseDto viewFaq(Long faqId) {
        Faq entity = faqRepository.findById(faqId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 FAQ입니다. faqId=" + faqId));


        return new FaqResponseDto(entity);
    }
}

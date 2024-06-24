package MIN.DosiNongBu.service.help;

import MIN.DosiNongBu.controller.help.dto.request.InquirySaveRequestDto;
import MIN.DosiNongBu.controller.help.dto.request.InquiryUpdateRequestDto;
import MIN.DosiNongBu.controller.help.dto.response.*;
import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.repository.help.FaqRepository;
import MIN.DosiNongBu.repository.help.InquiryRepository;
import MIN.DosiNongBu.repository.help.NoticeRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class HelpServiceImplTest {

    @Autowired HelpService helpService;

    @Autowired UserRepository userRepository;

    @Autowired InquiryRepository inquiryRepository;
    @Autowired NoticeRepository noticeRepository;
    @Autowired FaqRepository faqRepository;

    @AfterEach
    void clear(){
        inquiryRepository.deleteAll();
        noticeRepository.deleteAll();
        faqRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void 문의_목록_조회(){
        log.debug("debug log= 문의 목록 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Inquiry inquiry = Inquiry.builder()
                .inquiryType(InquiryType.GENERAL_INQUIRY)
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        inquiry.setUser(user);
        inquiryRepository.save(inquiry);

        Pageable pageable = PageRequest.of(0, 5);

        // when
        List<InquiryListResponseDto> responseDto = helpService.viewInquiryList(pageable);

        // then
        assertThat(responseDto.get(0).getTitle()).isEqualTo("테스트 제목");
        assertThat(responseDto.get(0).getAuthor()).isEqualTo("테스트");
        assertThat(responseDto.get(0).getInquiryType()).isEqualTo(InquiryType.GENERAL_INQUIRY);
    }

    @Test
    void 문의_조회(){
        log.debug("debug log= 문의 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Inquiry inquiry = Inquiry.builder()
                .inquiryType(InquiryType.GENERAL_INQUIRY)
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        inquiry.setUser(user);
        inquiryRepository.save(inquiry);

        Long findInquiryId = inquiry.getInquiryId();

        // when
        InquiryResponseDto responseDto = helpService.viewInquiry(findInquiryId);

        // then
        assertThat(responseDto.getTitle()).isEqualTo("테스트 제목");
        assertThat(responseDto.getContent()).isEqualTo("테스트 본문");
        assertThat(responseDto.getImageUrls().get(0)).isEqualTo("테스트 이미지 1");
        assertThat(responseDto.getInquiryStatusType()).isEqualTo(InquiryStatusType.NO);
    }

    @Test
    void 문의_작성(){
        log.debug("debug log= 문의 작성");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Long findUserId = user.getUserId();

        InquirySaveRequestDto requestDto = InquirySaveRequestDto.builder()
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        // when
        Long saveInquiryId = helpService.registerInquiry(findUserId, InquiryType.GENERAL_INQUIRY, requestDto);

        // then
        Inquiry inquiry = inquiryRepository.findById(saveInquiryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문의입니다. inquiryId=" + saveInquiryId));

        assertThat(inquiry.getInquiryType()).isEqualTo(InquiryType.GENERAL_INQUIRY);
        assertThat(inquiry.getTitle()).isEqualTo("테스트 제목");
        assertThat(inquiry.getContent()).isEqualTo("테스트 본문");
        assertThat(inquiry.getImageUrls().get(0)).isEqualTo("테스트 이미지 1");
        assertThat(inquiry.getInquiryStatusType()).isEqualTo(InquiryStatusType.NO);
    }

    @Test
    void 문의_수정(){
        log.debug("debug log= 문의 수정");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Inquiry inquiry = Inquiry.builder()
                .inquiryType(InquiryType.GENERAL_INQUIRY)
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        inquiry.setUser(user);
        inquiryRepository.save(inquiry);

        Long findInquiryId = inquiry.getInquiryId();

        InquiryUpdateRequestDto requestDto = InquiryUpdateRequestDto.builder()
                .inquiryType("ACCOUNT_ISSUE")
                .title("수정된 테스트 제목")
                .content("수정된 테스트 본문")
                .imageUrls(imageUrls)
                .build();

        // when
        Long updateInquiryId = helpService.updateInquiry(findInquiryId, requestDto);

        // then
        Inquiry findInquiry = inquiryRepository.findById(updateInquiryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문의입니다. inquiryId=" + updateInquiryId));

        assertThat(inquiry.getInquiryType()).isEqualTo(InquiryType.ACCOUNT_ISSUE);
        assertThat(inquiry.getTitle()).isEqualTo("수정된 테스트 제목");
        assertThat(inquiry.getContent()).isEqualTo("수정된 테스트 본문");
        assertThat(inquiry.getImageUrls().get(0)).isEqualTo("테스트 이미지 1");
        assertThat(inquiry.getInquiryStatusType()).isEqualTo(InquiryStatusType.NO);
    }

    @Test
    void 문의_삭제(){
        log.debug("debug log= 문의 삭제");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Inquiry inquiry = Inquiry.builder()
                .inquiryType(InquiryType.GENERAL_INQUIRY)
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        inquiry.setUser(user);
        inquiryRepository.save(inquiry);

        Long findInquiryId = inquiry.getInquiryId();

        // when
        Long deleteInquiryId = helpService.deleteInquiry(findInquiryId);

        // then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            inquiryRepository.findById(deleteInquiryId)
                    .orElseThrow(() -> new IllegalStateException("존재하지 않는 문의입니다. inquiryId=" + deleteInquiryId));
        });
        assertThat(exception.getMessage()).isEqualTo("존재하지 않는 문의입니다. inquiryId=" + deleteInquiryId);
    }

    @Test
    void 공지사항_목록_조회(){
        log.debug("debug log= 공지사항 목록 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Notice notice = Notice.builder()
                .noticeType(NoticeType.MAINTENANCE_NOTICE)
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        noticeRepository.save(notice);

        Pageable pageable = PageRequest.of(0, 5);

        // when
        List<NoticeListResponseDto> responseDto = helpService.viewNoticeList(pageable);

        // then
        assertThat(responseDto.get(0).getTitle()).isEqualTo("테스트 제목");
    }

    @Test
    void 공지사항_조회(){
        log.debug("debug log= 공지사항 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Notice notice = Notice.builder()
                .noticeType(NoticeType.MAINTENANCE_NOTICE)
                .title("테스트 제목")
                .content("테스트 본문")
                .imageUrls(imageUrls)
                .build();
        noticeRepository.save(notice);

        Long findNoticeId = notice.getNoticeId();

        // when
        NoticeResponseDto responseDto = helpService.viewNotice(findNoticeId);

        // then
        assertThat(responseDto.getNoticeType()).isEqualTo(NoticeType.MAINTENANCE_NOTICE);
        assertThat(responseDto.getTitle()).isEqualTo("테스트 제목");
        assertThat(responseDto.getContent()).isEqualTo("테스트 본문");
        assertThat(responseDto.getImageUrls().get(0)).isEqualTo("테스트 이미지 1");
    }

    @Test
    void faq_목록_조회(){
        log.debug("debug log= faq 목록 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Faq faq = Faq.builder()
                .faqType(FaqType.ACCOUNT_FAQ)
                .faqQuestion("테스트 질문")
                .faqAnswer("테스트 답변")
                .imageUrls(imageUrls)
                .build();
        faqRepository.save(faq);

        Pageable pageable = PageRequest.of(0, 5);

        // when
        List<FaqListResponseDto> responseDto = helpService.viewFaqList(pageable);

        // then
        assertThat(responseDto.get(0).getTitle()).isEqualTo("테스트 질문");
    }

    @Test
    void faq_조회(){
        log.debug("debug log= faq 조회");

        // given
        User user = User.builder()
                .email("테스트 계정")
                .name("테스트")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("테스트 이미지 1");
        imageUrls.add("테스트 이미지 2");

        Faq faq = Faq.builder()
                .faqType(FaqType.ACCOUNT_FAQ)
                .faqQuestion("테스트 질문")
                .faqAnswer("테스트 답변")
                .imageUrls(imageUrls)
                .build();
        faqRepository.save(faq);

        Long faqId = faq.getFaqId();

        // when
        FaqResponseDto responseDto = helpService.viewFaq(faqId);

        // then
        assertThat(responseDto.getFaqType()).isEqualTo(FaqType.ACCOUNT_FAQ);
        assertThat(responseDto.getQuestion()).isEqualTo("테스트 질문");
        assertThat(responseDto.getAnswer()).isEqualTo("테스트 답변");
        assertThat(responseDto.getImageUrls().get(0)).isEqualTo("테스트 이미지 1");
    }
}
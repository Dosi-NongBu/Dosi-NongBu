package MIN.DosiNongBu.service.admin;

import MIN.DosiNongBu.controller.admin.dto.request.*;
import MIN.DosiNongBu.controller.admin.dto.response.UserListResponseDto;
import MIN.DosiNongBu.controller.admin.dto.response.UserResponseDto;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManagement;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.FaqType;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.help.constant.InquiryType;
import MIN.DosiNongBu.domain.help.constant.NoticeType;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.PostReport;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.repository.crop.CropPeriodRepository;
import MIN.DosiNongBu.repository.crop.CropRepository;
import MIN.DosiNongBu.repository.help.FaqRepository;
import MIN.DosiNongBu.repository.help.InquiryRepository;
import MIN.DosiNongBu.repository.help.NoticeRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AdminServiceImplTest {

    @Autowired UserRepository userRepository;

    @Autowired CropRepository cropRepository;

    @Autowired InquiryRepository inquiryRepository;
    @Autowired NoticeRepository noticeRepository;
    @Autowired FaqRepository faqRepository;

    @Autowired AdminService adminService;

    @AfterEach
    void clear() {
        cropRepository.deleteAll();
        inquiryRepository.deleteAll();
        noticeRepository.deleteAll();
        faqRepository.deleteAll();
        userRepository.deleteAll();
    }
    
    @Test
    void 신규_작물_등록() {
        log.debug("debug log= 신규 작물 등록");

        // given
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://cityfarmer.seoul.go.kr/fileManager/www/brd/6261/1617605980762.jpg");

        NewCropRequestDto requestDto = NewCropRequestDto.builder()
                .name("상추")
                .difficulty(1)
                .maxTemperature(20)
                .minTemperature(15)
                .humidity(50)
                .month(5)
                .imageUrls(imageUrls)
                .classification("종")
                .origin("유럽, 서아시아")
                .feature("상추에는 육류에 부족한 비타민C와 베타카로틴, 섬 유질을 보충해주고 체내 콜레스테롤이 쌓이는 것을 막아주고 피를 맑게 해주는 작용을 하므로 고기 먹을 때 같이 섭취하면 좋습니다.")
                .planting(" 본잎이 5-6장 정도 자란 모종을 심습니다. 잎이 병해충 피해나 상처가 없고, 뿌리는 하얗고 굵게 잘 자란 모종을 고릅니다." +
                        "20x20cm 간격으로 모종의 뿌리부분이 들어갈 정도의 구멍을 만들고 모종을 넣습니다. 뿌리와 토양이 밀착되도록 심어줍니다.\n" +
                        "뿌리가 충분히 젖을 정도로 물을 줍니다. 물이 충분히 스며든 후에는 뿌리의 위쪽 표면이 살짝 보일 정도로 흙을 덮어줍니다.")
                .cultivation("상추는 심은 후 대략 4-6주 내에 수확할 준비가 됩니다.\n" +
                        "잎이 부드럽고 싱싱하며 적당한 크기(20-30cm 정도)에 도달했을 때가 수확하기 가장 좋은 시기입니다.\n" +
                        "뜨거운 날씨가 오기 전에 아침 시간에 수확하는 것이 좋습니다. 이때 상추의 수분 함량이 가장 높고 신선합니다.\n" +
                        "잎 상추의 경우, 바깥쪽 잎부터 하나씩 조심스럽게 손으로 또는 가위로 자릅니다. 중앙의 어린 잎은 계속 성장할 수 있도록 남겨둡니다.\n")
                .pest("진딧물, 시들음병, 무름병")
                .tip("상추에서 여름에 흔히 발생하는 잎 끝이나, 생장점이 타들어가는 듯 갈변하는 현상을 엽소현상(팁번)이라 합니다. \n" +
                        "\n" +
                        "주로 여름철 고온과 장일에 의한 생리장애로 칼슘이 부족할 때 생장점 부근의 어린 세포가 기계적으로 파괴되어 생기는 증상입니다.\n" +
                        "\n" +
                        "적절한 칼슘제나 칼슘보충제를 공급해주어야 합니다.")
                .harvestManage(" 수확한 상추는 흙이나 이물질이 붙어 있을 수 있으므로 깨끗이 씻어주는 것이 중요합니다. 찬물에 상추를 여러 번 헹군 후 부드럽게 물기를 제거해주세요. 세척 시 너무 세게 문지르면 잎이 상할 수 있으니 주의하세요.\n" +
                        "\n" +
                        " 상추는 습기를 좋아하지만 과도한 물기는 부패를 빠르게 할 수 있습니다. 씻은 상추는 키친타월 등으로 감싸서 물기를 조절해주세요.  상추를 비닐봉지나 밀폐용기에 넣어 냉장 보관하면 신선도를 오래 유지할 수 있습니다. 공기 순환이 될 수 있도록 봉지에 몇 개의 구멍을 뚫어주는 것도 좋은 방법입니다.\n" +
                        "\n" +
                        "\n" +
                        "상추가 마르지 않도록 주기적으로 확인하고 필요한 경우, 키친타월을 촉촉하게 해서 수분을 조절해주세요.")
                .water(3)
                .ventilation(3)
                .repot(150)
                .pruning(null)
                .build();

        // when
        Long findCropId = adminService.registerNewCrop(requestDto);

        Crop crop = cropRepository.findById(findCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. cropId=" + findCropId));

        CropInformation cropInformation = crop.getCropInformation();
        CropManagement cropManagement = crop.getCropManagement();
        List<CropPeriod> cropPeriods = crop.getCropPeriods();

        // then
        assertThat(crop.getName()).isEqualTo("상추");
        assertThat(crop.getDifficulty()).isEqualTo(1);
        assertThat(crop.getMaxTemperature()).isEqualTo(20);
        assertThat(crop.getMinTemperature()).isEqualTo(15);
        assertThat(crop.getHumidity()).isEqualTo(50);
        assertThat(crop.getMonth()).isEqualTo(5);
        assertThat(crop.getImageUrls().get(0)).isEqualTo(imageUrls.get(0));

        assertThat(cropInformation.getClassification()).isEqualTo("종");
        assertThat(cropInformation.getOrigin()).isEqualTo("유럽, 서아시아");
    }

    @Test
    void 사용자_정보_목록_조회() {
        log.debug("debug log= 사용자 정보 목록 조회");

        // given
        User user1 = User.builder()
                .email("테스트 계정 1")
                .name("테스트 1")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .email("테스트 계정 2")
                .name("테스트 2")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .email("테스트 계정 3")
                .name("테스트 2")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user3);

        // when
        Pageable pageable = PageRequest.of(0, 3);

        List<UserListResponseDto> responseDto = adminService.viewUserList(pageable);

        // then
        assertThat(responseDto.get(0).getEmail()).isEqualTo("테스트 계정 1");
        assertThat(responseDto.get(1).getEmail()).isEqualTo("테스트 계정 2");
        assertThat(responseDto.get(2).getEmail()).isEqualTo("테스트 계정 3");
        assertThat(responseDto.size()).isEqualTo(3);

    }

    @Test
    void 사용자_정보_조회() {
        log.debug("debug log= 사용자 정보 조회");

        // given
        User user1 = User.builder()
                .email("테스트 계정 1")
                .name("테스트 1")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .email("테스트 계정 2")
                .name("테스트 2")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .email("테스트 계정 3")
                .name("테스트 2")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user3);

        // when
        Long findUserId = user2.getUserId();

        UserResponseDto responseDto = adminService.viewUser(findUserId);

        // then
        assertThat(responseDto.getEmail()).isEqualTo("테스트 계정 2");
        assertThat(responseDto.getRoleType()).isEqualTo(RoleType.USER);

    }

    @Test
    void 사용자_정보_수정() {
        log.debug("debug log= 사용자 정보 수정");

        // given
        User user1 = User.builder()
                .email("테스트 계정 1")
                .name("테스트 1")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user1);

        User user2 = User.builder()
                .email("테스트 계정 2")
                .name("테스트 2")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .email("테스트 계정 3")
                .name("테스트 2")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user3);

        // when
        Long findUserId = user2.getUserId();

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .nickname("수정된 테스트 계정 2")
                .profileImage("수정된 이미지 2")
                .roleType(RoleType.ADMIN)
                .build();

        Long updatedUserId = adminService.updateUser(findUserId, requestDto);

        User user = userRepository.findById(updatedUserId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + updatedUserId));

        // then
        assertThat(user.getName()).isEqualTo("테스트 2");
        assertThat(user.getNickname()).isEqualTo("수정된 테스트 계정 2");
        assertThat(user.getProfileImage()).isEqualTo("수정된 이미지 2");
        assertThat(user.getRole()).isEqualTo(RoleType.ADMIN);
    }

    @Test
    void 문의_답변_등록() {
        log.debug("debug log= 문의 답변 등록");

        // given
        User user = User.builder()
                .email("테스트 계정 1")
                .name("테스트 1")
                .role(RoleType.USER)
                .provider(ProviderType.DEFAULT)
                .build();
        userRepository.save(user);

        Inquiry inquiry = Inquiry.builder()
                .inquiryType(InquiryType.ACCOUNT_ISSUE)
                .title("테스트 문의 제목")
                .content("테스트 문의 본문")
                .build();
        inquiry.setUser(user);
        inquiryRepository.save(inquiry);

        InquiryAnswerRequestDto requestDto = InquiryAnswerRequestDto.builder()
                .inquiryStatusType(InquiryStatusType.COMPLETE)
                .answer("테스트 문의 답변")
                .build();

        // when
        Long findInquiryId = inquiry.getInquiryId();
        Long updateInquiryId = adminService.registerInquiryAnswer(findInquiryId, requestDto);

        Inquiry findInquiry = inquiryRepository.findById(updateInquiryId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 문의입니다. inquiryId=" + updateInquiryId));

        // then
        assertThat(findInquiry.getInquiryType()).isEqualTo(InquiryType.ACCOUNT_ISSUE);
        assertThat(findInquiry.getTitle()).isEqualTo("테스트 문의 제목");
        assertThat(findInquiry.getContent()).isEqualTo("테스트 문의 본문");
        assertThat(findInquiry.getInquiryStatusType()).isEqualTo(InquiryStatusType.COMPLETE);
        assertThat(findInquiry.getInquiryAnswer()).isEqualTo("테스트 문의 답변");
    }

    @Test
    void 공지사항_등록() {
        log.debug("debug log= 공지사항 등록");

        // given
        NoticeSaveRequestDto requestDto = NoticeSaveRequestDto.builder()
                .noticeType(NoticeType.ANNOUNCEMENT)
                .title("테스트 공지사항 제목")
                .content("테스트 공지사항 본문")
                .build();

        // when
        Long findNoticeId = adminService.registerNotice(requestDto);

        Notice notice = noticeRepository.findById(findNoticeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다. noticeId=" + findNoticeId));

        // then
        assertThat(notice.getNoticeType()).isEqualTo(NoticeType.ANNOUNCEMENT);
        assertThat(notice.getTitle()).isEqualTo("테스트 공지사항 제목");
        assertThat(notice.getContent()).isEqualTo("테스트 공지사항 본문");
    }

    @Test
    void 공지사항_수정() {
        log.debug("debug log= 공지사항 수정");

        // given
        Notice notice = Notice.builder()
                .noticeType(NoticeType.ANNOUNCEMENT)
                .title("테스트 공지사항 제목")
                .content("테스트 본문 제목")
                .build();
        noticeRepository.save(notice);

        NoticeUpdateRequestDto requestDto = NoticeUpdateRequestDto.builder()
                .noticeType(NoticeType.MAINTENANCE_NOTICE)
                .title("수정된 테스트 공지사항 제목")
                .content("수정된 테스트 본문 제목")
                .build();

        // when
        Long findNoticeId = notice.getNoticeId();
        Long updatedNoticeId = adminService.updateNotice(findNoticeId, requestDto);

        Notice updatedNotice = noticeRepository.findById(findNoticeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다. noticeId=" + findNoticeId));

        // then
        assertThat(updatedNotice.getNoticeType()).isEqualTo(NoticeType.MAINTENANCE_NOTICE);
        assertThat(updatedNotice.getTitle()).isEqualTo("수정된 테스트 공지사항 제목");
        assertThat(updatedNotice.getContent()).isEqualTo("수정된 테스트 본문 제목");

    }

    @Test
    void FAQ_등록() {
        log.debug("debug log= FAQ 등록");

        // given
        FaqSaveRequestDto requestDto = FaqSaveRequestDto.builder()
                .faqType(FaqType.ACCOUNT_FAQ)
                .question("테스트 질문")
                .answer("테스트 답변")
                .build();

        // when
        Long findFaqId = adminService.registerFAQ(requestDto);

        Faq faq = faqRepository.findById(findFaqId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FAQ입니다. faqId=" + findFaqId));

        // then
        assertThat(faq.getFaqType()).isEqualTo(FaqType.ACCOUNT_FAQ);
        assertThat(faq.getFaqQuestion()).isEqualTo("테스트 질문");
        assertThat(faq.getFaqAnswer()).isEqualTo("테스트 답변");
    }

    @Test
    void FAQ_수정() {
        log.debug("debug log= FAQ 수정");

        // given
        Faq faq = Faq.builder()
                .faqType(FaqType.ACCOUNT_FAQ)
                .faqQuestion("테스트 질문")
                .faqAnswer("테스트 답변")
                .build();
        faqRepository.save(faq);

        FaqUpdateRequestDto requestDto = FaqUpdateRequestDto.builder()
                .faqType(FaqType.BILLING_FAQ)
                .question("수정된 테스트 질문")
                .answer("수정된 테스트 답변")
                .build();

        // when
        Long findFaqId = faq.getFaqId();
        Long updatedFaqId = adminService.updateFAQ(findFaqId, requestDto);

        Faq updatedFaq = faqRepository.findById(updatedFaqId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FAQ입니다. faqId=" + updatedFaqId));

        // then
        assertThat(updatedFaq.getFaqType()).isEqualTo(FaqType.BILLING_FAQ);
        assertThat(updatedFaq.getFaqQuestion()).isEqualTo("수정된 테스트 질문");
        assertThat(updatedFaq.getFaqAnswer()).isEqualTo("수정된 테스트 답변");
    }

}
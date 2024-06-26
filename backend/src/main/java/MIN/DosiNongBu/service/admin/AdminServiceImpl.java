package MIN.DosiNongBu.service.admin;

import MIN.DosiNongBu.controller.admin.dto.request.*;
import MIN.DosiNongBu.controller.admin.dto.response.UserListResponseDto;
import MIN.DosiNongBu.controller.admin.dto.response.UserResponseDto;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManagement;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.help.Faq;
import MIN.DosiNongBu.domain.help.Inquiry;
import MIN.DosiNongBu.domain.help.Notice;
import MIN.DosiNongBu.domain.help.constant.InquiryStatusType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import MIN.DosiNongBu.repository.crop.CropInformationRepository;
import MIN.DosiNongBu.repository.crop.CropManagementRepository;
import MIN.DosiNongBu.repository.crop.CropPeriodRepository;
import MIN.DosiNongBu.repository.crop.CropRepository;
import MIN.DosiNongBu.repository.help.FaqRepository;
import MIN.DosiNongBu.repository.help.InquiryRepository;
import MIN.DosiNongBu.repository.help.NoticeRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;

    private final InquiryRepository inquiryRepository;
    private final NoticeRepository noticeRepository;
    private final FaqRepository faqRepository;

    private final CropRepository cropRepository;
    private final CropInformationRepository cropInformationRepository;
    private final CropManagementRepository cropManagementRepository;
    private final CropPeriodRepository cropPeriodRepository;

    @Override
    @Transactional
    public Long registerNewCrop(NewCropRequestDto requestDto) {
        Crop crop = requestDto.toCropEntity();
        CropInformation cropInformation = requestDto.toCropInformationEntity();
        CropManagement cropManagement = requestDto.toCropManagementEntity();
        List<CropPeriod> cropPeriods = requestDto.toCropPeriodEntity();

        cropInformation.setCrop(crop);
        cropInformationRepository.save(cropInformation);

        cropManagement.setCrop(crop);
        cropManagementRepository.save(cropManagement);

        for (CropPeriod cropPeriod : cropPeriods)
            cropPeriod.setCrop(crop);
        cropPeriodRepository.saveAll(cropPeriods);

        crop.setCropInformation(cropInformation);
        crop.setCropManagement(cropManagement);
        crop.setCropPeriods(cropPeriods);
        cropRepository.save(crop);

        return crop.getCropId();
    }

    @Override
    public List<UserListResponseDto> viewUserList(Pageable pageable) {
        Page<User> entity = userRepository.findAll(pageable);

        return entity.stream().map(UserListResponseDto::new).toList();
    }

    @Override
    public UserResponseDto viewUser(Long userId) {
        User entity =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        return new UserResponseDto(entity);
    }

    @Override
    @Transactional
    public void registerUser() {

    }

    @Override
    @Transactional
    public Long updateUser(Long userId, UserUpdateRequestDto requestDto) {
        User entity =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        entity.update(requestDto.getNickname(), requestDto.getProfileImage(), RoleType.valueOf(requestDto.getRoleType()));
        return entity.getUserId();
    }

    @Override
    @Transactional
    public Long registerInquiryAnswer(Long inquiryId, InquiryAnswerRequestDto requestDto) {
        Inquiry entity = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 문의입니다. inquiryId=" + inquiryId));

        entity.answer(InquiryStatusType.valueOf(requestDto.getInquiryStatusType().toUpperCase()), requestDto.getAnswer());
        return entity.getInquiryId();
    }

    @Override
    public void updateInquiryAnswer() {

    }

    @Override
    @Transactional
    public Long registerNotice(NoticeSaveRequestDto requestDto) {
        Notice entity = requestDto.toEntity();

        noticeRepository.save(entity);
        return entity.getNoticeId();
    }

    @Override
    @Transactional
    public Long updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto) {
        Notice entity = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항입니다. noticeId=" + noticeId));

        entity.update(requestDto.getNoticeType(), requestDto.getTitle(), requestDto.getContent(), requestDto.getImageUrls());
        return entity.getNoticeId();
    }

    @Override
    @Transactional
    public Long registerFAQ(FaqSaveRequestDto requestDto) {
        Faq entity = requestDto.toEntity();

        faqRepository.save(entity);
        return entity.getFaqId();
    }

    @Override
    @Transactional
    public Long updateFAQ(Long faqId, FaqUpdateRequestDto requestDto) {
        Faq entity = faqRepository.findById(faqId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FAQ입니다. faqId=" + faqId));

        entity.update(requestDto.getFaqType(), requestDto.getQuestion(), requestDto.getAnswer(), requestDto.getImageUrls());
        return entity.getFaqId();
    }
}

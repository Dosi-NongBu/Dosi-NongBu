package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserCropRepository;
import MIN.DosiNongBu.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserCropManageServiceImpl implements UserCropManageService{

    private final UserRepository userRepository;
    private final UserCropRepository userCropRepository;

    @Override
    public List<UserCropListResponseDto> findUserCropList(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        List<UserCrop> entity = user.getUserCrops();
        return entity.stream().map(UserCropListResponseDto::new).toList();
    }

    @Override
    public UserCropResponseDto viewUserCrop(Long userCropId) {
        UserCrop entity = userCropRepository.findById(userCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + userCropId));


        return new UserCropResponseDto(entity);
    }
}

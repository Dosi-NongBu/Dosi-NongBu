package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCropManageService {

    //내 작물 목록 조회
    List<UserCropListResponseDto> findUserCropList(Long userId, Pageable pageable);

    //내 작물 조회
    UserCropResponseDto viewUserCrop(Long userCropId);
}

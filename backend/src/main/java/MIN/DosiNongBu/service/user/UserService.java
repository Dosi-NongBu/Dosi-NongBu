package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.*;
import MIN.DosiNongBu.domain.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    /* 내 프로필 */
    ProfileResponseDto findProfile(Long userId);
    Long updateProfile(Long userId, ProfileUpdateRequestDto requestDto);

    /* 내 공간 */
    List<PlaceListResponseDto> findPlaceList(Long userId);
    Long savePlace(Long userId, PlaceSaveRequestDto placeSaveRequestDto);
    void deletePlace(Long placeId);

    //내가 쓴 글 목록 조회
    List<UserPostListResponseDto> findUserPostList(Long userId, Pageable pageable);


}

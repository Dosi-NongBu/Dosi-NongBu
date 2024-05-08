package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.ProfileResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserPostListResponseDto;
import MIN.DosiNongBu.domain.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(String email);

    Optional<User> findOne(Long userId);


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

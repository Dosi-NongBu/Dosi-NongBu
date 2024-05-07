package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.*;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.PostRepository;
import MIN.DosiNongBu.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserPlaceRepository userPlaceRepository;
    private final UserCropRepository userCropRepository;

    private final PostRepository postRepository;


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. email=" + email));
    }

    /* USER PROFILE */
    @Override
    public ProfileResponseDto findProfile(Long userId) {
        User entity =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        return new ProfileResponseDto(entity);
    }

    @Override
    public Long updateProfile(Long userId, ProfileUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        user.update(requestDto.getNickname(), requestDto.getAddress(), requestDto.getProfileImage());
        return userId;
    }

    /* USER PLACE */
    @Override
    public List<PlaceListResponseDto> findPlaceList(Long userId) {
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        List<UserPlace> entity = user.getUserPlaces();
        return entity.stream().map(PlaceListResponseDto::new).toList();
    }

    @Override
    public Long savePlace(Long userId, PlaceSaveRequestDto requestDto) {
        UserPlace userPlace = requestDto.toEntity();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        userPlace.setUser(user);
        userPlaceRepository.save(userPlace);

        return userPlace.getUserPlaceId();
    }

    @Override
    public void deletePlace(Long placeId) {
        userPlaceRepository.deleteById(placeId);
    }


    /* USER POST */
    @Override
    public List<UserPostListResponseDto> findUserPostList(Long userId, Pageable pageable) {
        Page<Post> entity = postRepository.findAllByUser_UserId(userId, pageable);

        return entity.stream().map(UserPostListResponseDto::new).toList();
    }


}

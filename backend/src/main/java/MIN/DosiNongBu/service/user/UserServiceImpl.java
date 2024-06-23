package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.*;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.repository.post.PostRepository;
import MIN.DosiNongBu.domain.user.*;
import MIN.DosiNongBu.repository.user.UserCropRepository;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserPlaceRepository userPlaceRepository;
    private final UserCropRepository userCropRepository;

    private final PostRepository postRepository;


    @Override
    public Optional<User> findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

    /* USER PROFILE */
    @Override
    public ProfileResponseDto viewProfile(Long userId) {
        User entity =  userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        return new ProfileResponseDto(entity);
    }

    @Override
    @Transactional
    public Long updateProfile(Long userId, ProfileUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        user.update(requestDto.getNickname(), requestDto.getAddress(), requestDto.getProfileImage());
        return userId;
    }

    /* USER PLACE */
    @Override
    public List<PlaceListResponseDto> viewPlaceList(Long userId) {
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        List<UserPlace> entity = user.getUserPlaces();
        return entity.stream().map(PlaceListResponseDto::new).toList();
    }

    @Override
    @Transactional
    public Long registerPlace(Long userId, PlaceSaveRequestDto requestDto) {
        UserPlace userPlace = requestDto.toEntity();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. userId=" + userId));

        System.out.println(requestDto.getPlaceType());

        // 연관 관계 연결
        userPlace.setUser(user);
        userPlaceRepository.save(userPlace);

        return userPlace.getUserPlaceId();
    }

    @Override
    @Transactional
    public void deletePlace(Long placeId) {
        userPlaceRepository.deleteById(placeId);
    }


    /* USER POST */
    @Override
    public List<UserPostListResponseDto> viewUserPostList(Long userId, Pageable pageable) {
        Page<Post> entity = postRepository.findByUser_UserId(userId, pageable);

        return entity.stream().map(UserPostListResponseDto::new).toList();
    }


}

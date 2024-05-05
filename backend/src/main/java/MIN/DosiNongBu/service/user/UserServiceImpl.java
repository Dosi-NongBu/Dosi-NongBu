package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.ProfileResponseDto;
import MIN.DosiNongBu.controller.user.dto.ProfileUpdateRequestDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserPlace;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserPlaceRepository userPlaceRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. email=" + email));
    }

    @Override
    public ProfileResponseDto findProfile(String email) {
        User entity =  userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다. email=" + email));

        return new ProfileResponseDto(entity);
    }

    @Override
    public void updateProfile(String email, ProfileUpdateRequestDto requestDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. email=" + email));

        user.update(requestDto.getNickname(), requestDto.getAddress(), requestDto.getProfileImage());
    }

    @Override
    public List<PlaceListResponseDto> findPlaceAll(String email) {
        return userPlaceRepository.findAll().stream()
                .map(PlaceListResponseDto::new)
                .toList();
    }

    @Override
    public void savePlace(PlaceSaveRequestDto requestDto) {
        UserPlace userPlace = requestDto.toEntity();

        userPlaceRepository.save(userPlace);
    }

    @Override
    public void deletePlace(Long Id) {

    }


}

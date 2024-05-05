package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.ProfileResponseDto;
import MIN.DosiNongBu.controller.user.dto.ProfileUpdateRequestDto;
import MIN.DosiNongBu.domain.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void deleteById(Long id);

    User findByEmail(String email);

    ProfileResponseDto findProfile(String email);

    void updateProfile(String email, ProfileUpdateRequestDto requestDto);

    List<PlaceListResponseDto> findPlaceAll(String email);

    void savePlace(PlaceSaveRequestDto placeSaveRequestDto);

    void deletePlace(Long id);





}

package MIN.DosiNongBu.controller.user;

import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.controller.user.dto.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.ProfileResponseDto;
import MIN.DosiNongBu.controller.user.dto.ProfileUpdateRequestDto;
import MIN.DosiNongBu.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 프로필 정보 조회
    @GetMapping("/users")
    public ProfileResponseDto user(String userEmail, Model model){
        ProfileResponseDto responseDto = userService.findProfile(userEmail);

        model.addAttribute("profile", responseDto);
        return responseDto;
    }

    //프로필 정보 수정
    @PutMapping("/users")
    public void updateUser(String userEmail, @RequestBody ProfileUpdateRequestDto requestDto){
        userService.updateProfile(userEmail, requestDto);
    }


    //내 공간 목록 조회
    @GetMapping("/userplaces")
    public void placeList(String userEmail, Model model){
        List<PlaceListResponseDto> responseDto = userService.findPlaceAll(userEmail);

        model.addAllAttributes(responseDto);
    }

    //공간 추가
    @PostMapping("/userplaces")
    public void createPlace(@RequestBody PlaceSaveRequestDto  requestDto){
        userService.savePlace(requestDto);

    }

    //내가 쓴 글 목록 조회
    @GetMapping("/userposts")
    public void userPostLists(){

    }


    //내 작물 목록 조회
    @GetMapping("/usercrops")
    public void userCropList(){

    }

    //내 작물 조회
    @GetMapping("/usercrops/{userCropId}")
    public void userCrop(@PathVariable Long userCropId, Model model){

        model.addAttribute("userCrop");

    }


}

package MIN.DosiNongBu.controller.user;

import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.ProfileResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserPostListResponseDto;
import MIN.DosiNongBu.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 유저 정보에 대한 API
* 마이페이지 & 내가 쓴 글 등
* */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public Long UserCookie(String cookie){
        Long userId = null;
        try {
            if(cookie != null){
                userId = Long.parseLong(cookie);
            }
        } catch (NumberFormatException e){
            //변환 할 수 없을 때
            throw new IllegalArgumentException("잘못된 유저 ID 입니다");
        }
        return userId;
    }


    // 프로필 정보 조회
    @GetMapping("/users")
    public ProfileResponseDto user(@CookieValue(name = "User") String cookie){
        log.info("프로필 정보 조회");
        Long userId = UserCookie(cookie);

        return userService.viewProfile(userId);
    }

    //프로필 정보 수정
    @PutMapping("/users")
    public Long updateUser(@CookieValue(name = "User") String cookie, @RequestBody ProfileUpdateRequestDto requestDto){
        log.info("프로필 정보 수정");
        Long userId = UserCookie(cookie);

        return userService.updateProfile(userId, requestDto);
    }


    //내 공간 목록 조회
    @GetMapping("/userplaces")
    public List<PlaceListResponseDto> placeList(@CookieValue(name = "User") String cookie){
        log.info("내 공간 목록 조회");
        Long userId = UserCookie(cookie);

        return userService.viewPlaceList(userId);
    }

    //내 공간 추가
    @PostMapping("/userplaces")
    public Long createPlace(@CookieValue(name = "User") String cookie, @RequestBody PlaceSaveRequestDto requestDto){
        log.info("내 공간 추가");
        Long userId = UserCookie(cookie);

        return userService.registerPlace(userId, requestDto);
    }

    //내 공간 삭제
    @DeleteMapping("/userplaces/{placeId}")
    public ResponseEntity<?> deletePlace(@PathVariable Long placeId){
        log.info("내 공간 삭제");
        userService.deletePlace(placeId);

        return ResponseEntity.ok("User Place Delete Success");
    }

    //내가 쓴 글 목록 조회
    @GetMapping("/userposts")
    public List<UserPostListResponseDto> userPostList(@CookieValue(name = "User") String cookie, Pageable pageable){
        log.info("내가 쓴 글 목록 조회");
        Long userId = UserCookie(cookie);

        return userService.viewUserPostList(userId, pageable);
    }





}

package MIN.DosiNongBu.controller.user;

import MIN.DosiNongBu.auth.jwt.JwtUtil;
import MIN.DosiNongBu.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 프로필 정보 조회
    @GetMapping("/users")
    public void user(HttpServletRequest request){
/*        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            userEmail = jwtUtil.extractClaim(authorizationHeader.substring(7), Claims::getSubject);
        }
        else{
            throw new IllegalStateException("오류");
        }*/
    }

    //프로필 정보 수정
    @PostMapping("/users")
    public void updateUser(){

    }


    //내 공간 목록 조회
    @GetMapping("/userplaces")
    public void placeList(){

    }

    //공간 추가
    @PostMapping("/userplaces")
    public void createPlace(){

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

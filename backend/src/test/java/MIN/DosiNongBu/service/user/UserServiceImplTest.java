package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.ProfileResponseDto;
import MIN.DosiNongBu.domain.post.PostRepository;
import MIN.DosiNongBu.domain.user.*;
import MIN.DosiNongBu.domain.user.constant.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired UserService userService;

    @Autowired UserRepository userRepository;
    @Autowired UserPlaceRepository userPlaceRepository;
    @Autowired UserCropRepository userCropRepository;
    @Autowired PostRepository postRepository;

    User user;
    UserPlace userPlace;

    @BeforeEach
    void add(){
        log.info("Debug : User 저장");
        user = User.builder()
                .email("test@naver.com")
                .name("테스트")
                .nickname("test")
                .provider(ProviderType.DEFAULT)
                .role(RoleType.USER)
                .build();
        userRepository.save(user);

        log.info("Debug : User Place 저장");
        userPlace = UserPlace.builder()
                .name("My Place")
                .place(PlaceType.TERRACE)
                .direction(DirectionType.EAST)
                .light(LightType.DIRECT)
                .quantity(QuantityType.MANY)
                .build();
        userPlace.setUser(user);
        userPlaceRepository.save(userPlace);
    }

    @AfterEach
    void clear(){
/*        userPlaceRepository.deleteAll();
        userRepository.deleteAll();
        userCropRepository.deleteAll();
        postRepository.deleteAll();*/
    }



/*    List<PlaceListResponseDto> findPlaceList(Long userId);
    Long savePlace(Long userId, PlaceSaveRequestDto placeSaveRequestDto);
    void deletePlace(Long placeId);
    //내가 쓴 글 목록 조회
    List<UserPostListResponseDto> findUserPostList(Long userId, Pageable pageable);*/

    @Test
    void 프로필_조회(){
        //given
        Long findUserId = user.getUserId();

        //when
        log.info("Debug : User Profile 조회");
        ProfileResponseDto responseDto = userService.findProfile(findUserId);


        //then
        assertThat(responseDto.getName()).isEqualTo("테스트");
        assertThat(responseDto.getNickname()).isEqualTo("test");
        assertThat(responseDto.getEmail()).isEqualTo("test@naver.com");
    }

    @Test
    @Transactional
    void 프로필_수정(){
        //given
        Long findUserId = user.getUserId();

        ProfileUpdateRequestDto requestDto = ProfileUpdateRequestDto.builder()
                .nickname("테스트수정")
                .address("테스트")
                .profileImage("테스트 URL")
                .build();

        //when
        log.info("Debug : User 정보 수정");
        Long updatedUserId = userService.updateProfile(findUserId, requestDto);
        log.info("Debug : 수정된 User 정보 조회");
        User updatedUser = userService.findOne(updatedUserId).get();

        //then
        assertThat(updatedUser.getNickname()).isEqualTo("테스트수정");
        assertThat(updatedUser.getCurrentAddress()).isEqualTo("테스트");
        assertThat(updatedUser.getProfileImage()).isEqualTo("테스트 URL");
    }

    @Test
    void 공간_목록_조회(){
        //given
        Long findUserId = user.getUserId();

        //when
        List<PlaceListResponseDto> responseDto = userService.findPlaceList(findUserId);

        //then
        assertThat(responseDto.get(0).getName()).isEqualTo("My Place");
    }

    @Test
    @Transactional
    void 공간_저장(){
        //given
        Long findUserId = user.getUserId();

        PlaceSaveRequestDto requestDto = PlaceSaveRequestDto.builder()
                .name("New Place 1")
                .place(PlaceType.TERRACE)
                .direction(DirectionType.SOUTH)
                .light(LightType.DIRECT)
                .quantity(QuantityType.MANY)
                .build();

        //when
        log.info("Debug : User Place 저장");
        Long savedUserPlaceId = userService.savePlace(findUserId, requestDto);

        UserPlace savedUserPlace = userPlaceRepository.findById(savedUserPlaceId).get();

        //then
        assertThat(savedUserPlace.getName()).isEqualTo("New Place 1");
        assertThat(savedUserPlace.getPlace()).isEqualTo(PlaceType.TERRACE);
        assertThat(savedUserPlace.getDirection()).isEqualTo(DirectionType.SOUTH);
        assertThat(savedUserPlace.getLight()).isEqualTo(LightType.DIRECT);
        assertThat(savedUserPlace.getQuantity()).isEqualTo(QuantityType.MANY);
    }

    void 공간_저장_초과(){
        //given

        //when

        //then
    }

    void 공간_삭제(){
        //given

        //when

        //then
    }

    void 내가_쓴_글_목록_조회(){
        //given

        //when

        //then
    }




}
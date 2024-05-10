package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.PlaceSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.ProfileUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.PlaceListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.ProfileResponseDto;
import MIN.DosiNongBu.repository.post.PostRepository;
import MIN.DosiNongBu.domain.user.*;
import MIN.DosiNongBu.domain.user.constant.*;
import MIN.DosiNongBu.repository.user.UserCropRepository;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
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

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPlaceRepository userPlaceRepository;
    @Autowired
    UserCropRepository userCropRepository;
    @Autowired PostRepository postRepository;

    User user;
    UserPlace userPlace;

    @BeforeEach
    void add(){
        log.debug("Debug : User 저장");
        user = User.builder()
                .email("test@naver.com")
                .name("테스트")
                .nickname("test")
                .provider(ProviderType.DEFAULT)
                .role(RoleType.USER)
                .build();
        userRepository.save(user);

        log.debug("Debug : User Place 저장");
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
        log.debug("debug log= DB 삭제");
        userPlaceRepository.deleteAll();
        userRepository.deleteAll();
        userCropRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    void 프로필_조회(){
        log.debug("debug log= 프로필 조회 테스트");

        //given
        Long findUserId = user.getUserId();

        //when
        log.debug("Debug : User Profile 조회");
        ProfileResponseDto responseDto = userService.viewProfile(findUserId);


        //then
        assertThat(responseDto.getName()).isEqualTo("테스트");
        assertThat(responseDto.getNickname()).isEqualTo("test");
        assertThat(responseDto.getEmail()).isEqualTo("test@naver.com");
    }

    @Test
    @Transactional
    void 프로필_수정(){
        log.debug("debug log= 프로필 수정 테스트");

        //given
        Long findUserId = user.getUserId();

        ProfileUpdateRequestDto requestDto = ProfileUpdateRequestDto.builder()
                .nickname("테스트수정")
                .address("테스트")
                .profileImage("테스트 URL")
                .build();

        //when
        log.debug("Debug : User 정보 수정");
        Long updatedUserId = userService.updateProfile(findUserId, requestDto);
        log.debug("Debug : 수정된 User 정보 조회");
        User updatedUser = userService.findOne(updatedUserId).get();

        //then
        assertThat(updatedUser.getNickname()).isEqualTo("테스트수정");
        assertThat(updatedUser.getCurrentAddress()).isEqualTo("테스트");
        assertThat(updatedUser.getProfileImage()).isEqualTo("테스트 URL");
    }

    @Test
    void 공간_목록_조회(){
        log.debug("debug log= 공간 목록 조회 테스트");

        //given
        Long findUserId = user.getUserId();

        //when
        List<PlaceListResponseDto> responseDto = userService.viewPlaceList(findUserId);

        //then
        assertThat(responseDto.get(0).getName()).isEqualTo("My Place");
    }

    @Test
    @Transactional
    void 공간_저장(){
        log.debug("debug log= 공간 저장 테스트");

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
        Long savedUserPlaceId = userService.registerPlace(findUserId, requestDto);

        UserPlace savedUserPlace = userPlaceRepository.findById(savedUserPlaceId).get();

        //then
        assertThat(savedUserPlace.getName()).isEqualTo("New Place 1");
        assertThat(savedUserPlace.getPlace()).isEqualTo(PlaceType.TERRACE);
        assertThat(savedUserPlace.getDirection()).isEqualTo(DirectionType.SOUTH);
        assertThat(savedUserPlace.getLight()).isEqualTo(LightType.DIRECT);
        assertThat(savedUserPlace.getQuantity()).isEqualTo(QuantityType.MANY);
    }

    @Test
    @Transactional
    void 공간_저장_초과(){
        log.debug("debug log= 공간 저장 초과 테스트");

        //given
        Long findUserId = user.getUserId();

        for(int i=1;i<=12;i++){
            PlaceSaveRequestDto requestDto = PlaceSaveRequestDto.builder()
                    .name("New Place " + i)
                    .place(PlaceType.TERRACE)
                    .direction(DirectionType.SOUTH)
                    .light(LightType.DIRECT)
                    .quantity(QuantityType.MANY)
                    .build();

            try {
                userService.registerPlace(findUserId, requestDto);
            } catch (IllegalStateException e) {
                // then
                assertThat(e.getMessage()).isEqualTo("사용자 공간은 최대 10개까지만 저장할 수 있습니다.");
            }
        }

        //when
        List<PlaceListResponseDto> responseDto = userService.viewPlaceList(findUserId);

        //then
        assertThat(responseDto.size()).isEqualTo(10);
    }

    @Test
    @Transactional
    void 공간_삭제(){
        log.debug("debug log= 공간 삭제 테스트");

        //given
        Long findUserId = user.getUserId();
        Long findPlaceId = userPlace.getUserPlaceId();

        //when
        log.debug("debug log= 공간 삭제");
        user.getUserPlaces().remove(userPlace);
        userService.deletePlace(findPlaceId);

        List<PlaceListResponseDto> responseDto = userService.viewPlaceList(findUserId);
        //then
        assertThat(responseDto.size()).isEqualTo(0);
    }

    void 내가_쓴_글_목록_조회(){
        //given

        //List<UserPostListResponseDto> findUserPostList(Long userId, Pageable pageable)
        //when

        //then
    }




}
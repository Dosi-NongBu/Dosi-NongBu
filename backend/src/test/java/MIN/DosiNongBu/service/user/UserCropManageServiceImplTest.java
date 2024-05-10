package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserPlace;
import MIN.DosiNongBu.repository.crop.CropRepository;
import MIN.DosiNongBu.repository.user.UserCropRepository;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
class UserCropManageServiceImplTest {

    @Autowired UserService userService;
    @Autowired UserCropManageService userCropManageService;

    @Autowired UserRepository userRepository;
    @Autowired UserPlaceRepository  userPlaceRepository;
    @Autowired UserCropRepository userCropRepository;
    @Autowired CropRepository cropRepository;


/*    //내 작물 목록 조회
    List<UserCropListResponseDto> findUserCropList(Long userId);
    //내 작물 조회
    UserCropResponseDto viewUserCrop(Long userCropId);*/
    User user;
    Crop crop;
    UserPlace userPlace;
    UserCrop userCrop;

    @BeforeEach
    void add(){
/*        log.debug("Debug : User 저장");
        user = User.builder()
                .email("test@naver.com")
                .name("테스트")
                .nickname("test")
                .provider(ProviderType.DEFAULT)
                .role(RoleType.USER)
                .build();
        userRepository.save(user);


        log.debug("Debug : UserPlace 저장");
        userPlace = UserPlace.builder()
                .user(user)
                .name("테스트 공간")
                .place(PlaceType.VERANDA)
                .direction(DirectionType.EAST)
                .light(LightType.DIRECT)
                .quantity(QuantityType.MANY)
                .build();
        userPlace.setUser(user);
        userPlaceRepository.save(userPlace);

        log.debug("Debug : UserCrop 저장");
        userCrop = UserCrop.builder()
                .name("상추")
                .nickname("테스트")
                .period(4)
                .perPeriod(3)
                .maxTemperature(15)
                .minTemperature(25)
                .humidity(50)
                .build();
        userCrop.setUser(user);
        userCrop.setUserPlace(userPlace);
        userCropRepository.save(userCrop);*/
    }

    @Test
    void 내_작묾_목록_조회(){
        log.debug("debug log= 내 작물 목록 조회 테스트");

        //given
        Long userId = user.getUserId();

        //when
        List<UserCropListResponseDto> responseDto = userCropManageService.findUserCropList(userId);

        //System.out.println(user.getUserCrops().get(0).getName());

        //then
        ///System.out.println(responseDto.size());
        //assertThat(responseDto.get(0).getId()).isEqualTo(userCrop.getUserCropId());
        //assertThat(responseDto.get(0).getNickname()).isEqualTo(userCrop.getNickname());
        //assertThat(responseDto.get(0).getImageUrl()).isEqualTo(null);
    }

    @Test
    void 내_작물_조회(){

    }

}
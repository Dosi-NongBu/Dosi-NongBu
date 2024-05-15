package MIN.DosiNongBu.service.user;

import MIN.DosiNongBu.controller.user.dto.request.UserCropAlarmUpdateRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropImageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.request.UserCropManageSaveRequestDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropAlarmResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropManageListResponseDto;
import MIN.DosiNongBu.controller.user.dto.response.UserCropResponseDto;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserPlace;
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
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class UserCropManageServiceImplTest {

    @Autowired UserService userService;
    @Autowired UserCropManageService userCropManageService;

    @Autowired UserRepository userRepository;
    @Autowired UserPlaceRepository  userPlaceRepository;
    @Autowired UserCropRepository userCropRepository;

    User user;
    UserPlace userPlace;
    UserCrop userCrop;

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


        log.debug("Debug : UserPlace 저장");
        userPlace = UserPlace.builder()
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
                .prePeriod(3)
                .maxTemperature(15)
                .minTemperature(25)
                .humidity(50)
                .build();
        userCrop.setUser(user);
        userCrop.setUserPlace(userPlace);
        userCropRepository.save(userCrop);
    }

    @AfterEach
    void clear(){
        log.debug("debug log= DB 삭제");

        userCropRepository.deleteAll();
        userPlaceRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void 내_작묾_목록_조회(){
        log.debug("debug log= 내 작물 목록 조회 테스트");

        //given
        Long userId = user.getUserId();

        //when
        List<UserCropListResponseDto> responseDto = userCropManageService.findUserCropList(userId);

        //then
        assertThat(responseDto.get(0).getId()).isEqualTo(userCrop.getUserCropId());
        assertThat(responseDto.get(0).getNickname()).isEqualTo(userCrop.getNickname());
        assertThat(responseDto.get(0).getImageUrl()).isEqualTo(null);
    }

    @Test
    void 내_작물_조회(){
        log.debug("debug log= 내 작물 목록 조회 테스트");

        //given
        Long userCropId = userCrop.getUserCropId();

        //when
        UserCropResponseDto responseDto = userCropManageService.viewUserCrop(userCropId);

        //then
        assertThat(responseDto.getName()).isEqualTo("상추");
        assertThat(responseDto.getNickname()).isEqualTo("테스트");
        assertThat(responseDto.getPeriod()).isEqualTo(4);
        assertThat(responseDto.getPrePeriod()).isEqualTo(3);
        assertThat(responseDto.getMaxTemperature()).isEqualTo(15);
        assertThat(responseDto.getMinTemperature()).isEqualTo(25);
        assertThat(responseDto.getHumidity()).isEqualTo(50);
    }

    //내 작물 알림 조회
    //UserCropAlarmResponseDto viewUserCropAlarm(Long userCropId);
    @Test
    void 내_작물_알림_조회(){

    }

    //내 작물 알림 수정
    //Long updateUserCropAlarm(Long userCropId, UserCropAlarmUpdateRequestDto requestDto);
    @Test
    void 내_작물_알림_수정() {

    }

    //내 작물 관리 목록 조회
    //List<UserCropManageListResponseDto> viewUserCropManageList(Long userCropId, Pageable pageable);
    @Test
    void 내_작물_관리_목록_조회(){

    }

    //내 작물 관리 추가
    //Long registerUserCropManage(Long userCropId, UserCropManageSaveRequestDto requestDto);
    @Test
    void 내_작물_관리_추가(){

    }

    //내 작물 관리 삭제
    //Long deleteUserCropManage(Long cropLogId);
    @Test
    void 내_작물_관리_삭제(){

    }

    //내 작물 사진 추가
    //Long updateUserCropImage(Long userCropId, UserCropImageSaveRequestDto requestDto);
    @Test
    void 내_작물_사진_추가(){

    }

}
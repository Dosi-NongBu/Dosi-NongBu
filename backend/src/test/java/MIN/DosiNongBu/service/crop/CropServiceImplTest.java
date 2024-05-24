package MIN.DosiNongBu.service.crop;

import MIN.DosiNongBu.controller.crop.dto.request.UserCropSaveRequestDto;
import MIN.DosiNongBu.controller.crop.dto.response.CropInfoResponseDto;
import MIN.DosiNongBu.controller.crop.dto.response.CropListResponseDto;
import MIN.DosiNongBu.controller.crop.dto.response.CropMainResponseDto;
import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManagement;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserCrop;
import MIN.DosiNongBu.domain.user.UserPlace;
import MIN.DosiNongBu.domain.user.constant.*;
import MIN.DosiNongBu.repository.crop.CropInformationRepository;
import MIN.DosiNongBu.repository.crop.CropManagementRepository;
import MIN.DosiNongBu.repository.crop.CropPeriodRepository;
import MIN.DosiNongBu.repository.crop.CropRepository;
import MIN.DosiNongBu.repository.user.UserCropRepository;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CropServiceImplTest {

    @Autowired CropService cropService;

    @Autowired UserRepository userRepository;
    @Autowired UserPlaceRepository userPlaceRepository;
    @Autowired UserCropRepository userCropRepository;

    @Autowired CropRepository cropRepository;
    @Autowired CropInformationRepository cropInformationRepository;
    @Autowired
    CropManagementRepository cropManagementRepository;
    @Autowired CropPeriodRepository cropPeriodRepository;

    Crop crop;
    User user;
    UserPlace userPlace;
    UserCrop userCrop;

    CropInformation cropInformation;
    CropManagement cropManagement;
    CropPeriod cropPeriod;

    @BeforeEach
    void add(){
        log.debug("debug log= Crop 저장");
        crop = Crop.builder()
                .name("상추")
                .difficulty(1)
                .maxTemperature(20)
                .minTemperature(15)
                .humidity(50)
                .month(5)
                .build();
        cropRepository.save(crop);

        cropInformation = CropInformation.builder()
                .classification("종")
                .origin("유럽, 서아시아")
                .feature("상추에는 육류에 부족한 비타민C와 베타카로틴, 섬 유질을 보충해주고 체내 콜레스테롤이 쌓이는 것을 막아주고 피를 맑게 해주는 작용을 하므로 고기 먹을 때 같이 섭취하면 좋습니다.")
                .build();
        cropInformation.setCrop(crop);
        cropInformationRepository.save(cropInformation);

        cropManagement = CropManagement.builder()
                .planting(" 본잎이 5-6장 정도 자란 모종을 심습니다. 잎이 병해충 피해나 상처가 없고, 뿌리는 하얗고 굵게 잘 자란 모종을 고릅니다." +
                        "20x20cm 간격으로 모종의 뿌리부분이 들어갈 정도의 구멍을 만들고 모종을 넣습니다. 뿌리와 토양이 밀착되도록 심어줍니다.\n" +
                        "뿌리가 충분히 젖을 정도로 물을 줍니다. 물이 충분히 스며든 후에는 뿌리의 위쪽 표면이 살짝 보일 정도로 흙을 덮어줍니다.")
                .cultivation("상추는 심은 후 대략 4-6주 내에 수확할 준비가 됩니다.\n" +
                        "잎이 부드럽고 싱싱하며 적당한 크기(20-30cm 정도)에 도달했을 때가 수확하기 가장 좋은 시기입니다.\n" +
                        "뜨거운 날씨가 오기 전에 아침 시간에 수확하는 것이 좋습니다. 이때 상추의 수분 함량이 가장 높고 신선합니다.\n" +
                        "잎 상추의 경우, 바깥쪽 잎부터 하나씩 조심스럽게 손으로 또는 가위로 자릅니다. 중앙의 어린 잎은 계속 성장할 수 있도록 남겨둡니다.\n")
                .pest("진딧물, 시들음병, 무름병")
                .tip("상추에서 여름에 흔히 발생하는 잎 끝이나, 생장점이 타들어가는 듯 갈변하는 현상을 엽소현상(팁번)이라 합니다. \n" +
                        "\n" +
                        "주로 여름철 고온과 장일에 의한 생리장애로 칼슘이 부족할 때 생장점 부근의 어린 세포가 기계적으로 파괴되어 생기는 증상입니다.\n" +
                        "\n" +
                        "적절한 칼슘제나 칼슘보충제를 공급해주어야 합니다.")
                .harvestManage(" 수확한 상추는 흙이나 이물질이 붙어 있을 수 있으므로 깨끗이 씻어주는 것이 중요합니다. 찬물에 상추를 여러 번 헹군 후 부드럽게 물기를 제거해주세요. 세척 시 너무 세게 문지르면 잎이 상할 수 있으니 주의하세요.\n" +
                        "\n" +
                        " 상추는 습기를 좋아하지만 과도한 물기는 부패를 빠르게 할 수 있습니다. 씻은 상추는 키친타월 등으로 감싸서 물기를 조절해주세요.  상추를 비닐봉지나 밀폐용기에 넣어 냉장 보관하면 신선도를 오래 유지할 수 있습니다. 공기 순환이 될 수 있도록 봉지에 몇 개의 구멍을 뚫어주는 것도 좋은 방법입니다.\n" +
                        "\n" +
                        "\n" +
                        "상추가 마르지 않도록 주기적으로 확인하고 필요한 경우, 키친타월을 촉촉하게 해서 수분을 조절해주세요.")
                .build();
        cropManagement.setCrop(crop);
        cropManagementRepository.save(cropManagement);

        for(CropManageType cropManageType : CropManageType.values()){
            cropPeriod = CropPeriod.builder()
                    .manage(cropManageType)
                    .period(4)
                    .build();
            cropPeriod.setCrop(crop);
            cropPeriodRepository.save(cropPeriod);
        }

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

    }

    @AfterEach
    void clear(){
/*        log.debug("debug log= DB 삭제");

        userCropRepository.deleteAll();
        userPlaceRepository.deleteAll();
        userRepository.deleteAll();*/
    }

    @Test
    void 작물_목록_조회(){
        log.debug("debug log= 작물 목록 조회");

        //given
        Pageable pageable = PageRequest.of(0, 10);

        //when
        List<CropListResponseDto> responseDto = cropService.viewCropList(pageable);

        //then
        assertThat(responseDto.get(0).getName()).isEqualTo("상추");
    }

    @Test
    void 작물_추천_목록_조회(){
        log.debug("debug log= 추천 작물 목록 조회");

        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        List<CropListResponseDto> responseDto = cropService.viewCropRecommendList(pageable);

        //then
        assertThat(responseDto.get(0).getName()).isEqualTo("상추");
    }

    @Test
    void 작물_검색(){
        log.debug("debug log= 작물 검색");

        // given
        Pageable pageable = PageRequest.of(0, 10);
        String keyword = "상";

        // when
        List<CropListResponseDto> responseDto = cropService.viewCropSearchList(keyword, pageable);

        //then
        assertThat(responseDto.get(0).getName()).isEqualTo("상추");
    }

    @Test
    void 작물_기본_정보(){
        log.debug("debug log= 작물 기본 정보");

        // given
        Long findCropId = crop.getCropId();

        // when
        CropMainResponseDto responseDto = cropService.viewCropMain(findCropId);

        //then
        assertThat(responseDto.getName()).isEqualTo(crop.getName());
        assertThat(responseDto.getDifficulty()).isEqualTo(crop.getDifficulty());
        assertThat(responseDto.getMaxTemperature()).isEqualTo(crop.getMaxTemperature());
        assertThat(responseDto.getMinTemperature()).isEqualTo(crop.getMinTemperature());
        assertThat(responseDto.getHumidity()).isEqualTo(crop.getHumidity());
        assertThat(responseDto.getMonth()).isEqualTo(crop.getMonth());

        assertThat(responseDto.getWater()).isEqualTo(4);
        assertThat(responseDto.getVentilation()).isEqualTo(4);
        assertThat(responseDto.getRepot()).isEqualTo(4);
        assertThat(responseDto.getPruning()).isEqualTo(4);
    }

    @Test
    void 작물_상세_정보_관리법(){
        log.debug("debug log= 작물 상세 정보 관리법");

        // given
        Long findCropId = crop.getCropId();

        // when
        CropInfoResponseDto responseDto = cropService.viewCropInfo(findCropId);

        //then
        assertThat(responseDto.getClassification()).isEqualTo(cropInformation.getClassification());
        assertThat(responseDto.getOrigin()).isEqualTo(cropInformation.getOrigin());
        assertThat(responseDto.getFeature()).isEqualTo(cropInformation.getFeature());

        assertThat(responseDto.getPlanting()).isEqualTo(cropManagement.getPlanting());
        assertThat(responseDto.getCultivation()).isEqualTo(cropManagement.getCultivation());
        assertThat(responseDto.getPest()).isEqualTo(cropManagement.getPest());
        assertThat(responseDto.getTip()).isEqualTo(cropManagement.getTip());
        assertThat(responseDto.getHarvest_manage()).isEqualTo(cropManagement.getHarvestManage());
    }

    @Test
    void 작물_키우기(){
        log.debug("debug log= 작물 키우기");

        // given
        Long findUserId = user.getUserId();
        Long findUserPlaceId = userPlace.getUserPlaceId();

        UserCropSaveRequestDto requestDto = UserCropSaveRequestDto.builder()
                .userPlaceId(findUserPlaceId)
                .name(crop.getName())
                .nickname("애칭")
                .period(4)
                .prePeriod(4)
                .maxTemperature(crop.getMaxTemperature())
                .minTemperature(crop.getMinTemperature())
                .humidity(crop.getHumidity())
                .build();

        // when
        Long savedUserCropId = cropService.registerUserCrop(findUserId, requestDto);

        UserCrop findUserCrop = userCropRepository.findById(savedUserCropId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 작물입니다. userCropId=" + savedUserCropId));

        //then
        assertThat(findUserCrop.getName()).isEqualTo(crop.getName());
        assertThat(findUserCrop.getNickname()).isEqualTo("애칭");
        assertThat(findUserCrop.getPeriod()).isEqualTo(4);
        assertThat(findUserCrop.getPrePeriod()).isEqualTo(4);
        assertThat(findUserCrop.getMaxTemperature()).isEqualTo(crop.getMaxTemperature());
        assertThat(findUserCrop.getMinTemperature()).isEqualTo(crop.getMinTemperature());
        assertThat(findUserCrop.getHumidity()).isEqualTo(crop.getHumidity());
    }


}
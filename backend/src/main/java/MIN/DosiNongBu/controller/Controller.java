package MIN.DosiNongBu.controller;

import MIN.DosiNongBu.domain.crop.Crop;
import MIN.DosiNongBu.domain.crop.CropInformation;
import MIN.DosiNongBu.domain.crop.CropManagement;
import MIN.DosiNongBu.domain.crop.CropPeriod;
import MIN.DosiNongBu.domain.crop.constant.CropManageType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.UserPlace;
import MIN.DosiNongBu.domain.user.constant.*;
import MIN.DosiNongBu.repository.crop.CropInformationRepository;
import MIN.DosiNongBu.repository.crop.CropManagementRepository;
import MIN.DosiNongBu.repository.crop.CropPeriodRepository;
import MIN.DosiNongBu.repository.crop.CropRepository;
import MIN.DosiNongBu.repository.user.UserPlaceRepository;
import MIN.DosiNongBu.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Transactional
public class Controller {

    private final UserRepository userRepository;
    private final UserPlaceRepository userPlaceRepository;

    private final CropRepository cropRepository;
    private final CropInformationRepository cropInformationRepository;
    private final CropManagementRepository cropManagementRepository;
    private final CropPeriodRepository cropPeriodRepository;

    @GetMapping("/api/hello")
    public String test() {
        return "테스트";
    }

    @GetMapping("/setup")
    public void setup() {
        log.debug("Debug : User 저장");
        User user = User.builder()
                .email("test@naver.com")
                .name("테스트")
                .nickname("test")
                .provider(ProviderType.DEFAULT)
                .role(RoleType.USER)
                .build();
        userRepository.save(user);

        log.debug("Debug : UserPlace 저장");
        UserPlace userPlace = UserPlace.builder()
                .name("테스트 공간")
                .place(PlaceType.VERANDA)
                .direction(DirectionType.EAST)
                .light(LightType.DIRECT)
                .quantity(QuantityType.MANY)
                .build();
        userPlace.setUser(user);
        userPlaceRepository.save(userPlace);

        log.debug("debug log= Crop 저장");
        Crop crop = Crop.builder()
                .name("상추")
                .difficulty(1)
                .maxTemperature(20)
                .minTemperature(15)
                .humidity(50)
                .month(5)
                .build();
        cropRepository.save(crop);

        CropInformation cropInformation = CropInformation.builder()
                .classification("종")
                .origin("유럽, 서아시아")
                .feature("상추에는 육류에 부족한 비타민C와 베타카로틴, 섬 유질을 보충해주고 체내 콜레스테롤이 쌓이는 것을 막아주고 피를 맑게 해주는 작용을 하므로 고기 먹을 때 같이 섭취하면 좋습니다.")
                .build();
        cropInformation.setCrop(crop);
        cropInformationRepository.save(cropInformation);

        CropManagement cropManagement = CropManagement.builder()
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
            CropPeriod cropPeriod = CropPeriod.builder()
                    .manageType(cropManageType)
                    .period(4)
                    .build();
            cropPeriod.setCrop(crop);
            cropPeriodRepository.save(cropPeriod);
        }

    }
}


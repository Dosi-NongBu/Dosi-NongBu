package MIN.DosiNongBu.service.admin;

import MIN.DosiNongBu.controller.admin.dto.request.NewCropRequestDto;
import MIN.DosiNongBu.domain.post.Post;
import MIN.DosiNongBu.domain.post.PostReport;
import MIN.DosiNongBu.domain.post.constant.PostType;
import MIN.DosiNongBu.domain.post.constant.ReportType;
import MIN.DosiNongBu.domain.user.User;
import MIN.DosiNongBu.domain.user.constant.ProviderType;
import MIN.DosiNongBu.domain.user.constant.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class AdminServiceImplTest {

    @Autowired AdminService adminService;




    @Test
    void 신규_작물_등록(){
        log.debug("debug log= 신규 작물 등록");

        // given
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("data:image/jpeg;base64,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");

        NewCropRequestDto requestDto = NewCropRequestDto.builder()
                .name("상추")
                .difficulty(1)
                .maxTemperature(20)
                .minTemperature(15)
                .humidity(50)
                .month(5)
                .imageUrls(imageUrls)
                .classification("종")
                .origin("유럽, 서아시아")
                .feature("상추에는 육류에 부족한 비타민C와 베타카로틴, 섬 유질을 보충해주고 체내 콜레스테롤이 쌓이는 것을 막아주고 피를 맑게 해주는 작용을 하므로 고기 먹을 때 같이 섭취하면 좋습니다.")
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
                .water(3)
                .ventilation(3)
                .repot(150)
                .pruning(null)
                .build();

        // when
        adminService.registerNewCrop(requestDto);

        // then

    }

    @Test
    void 사용자_정보_목록_조회(){
        log.debug("debug log= 사용자 정보 목록 조회");

        // given


        // when

        // then

    }

    @Test
    void 사용자_정보_조회(){
        log.debug("debug log= 사용자 정보 조회");

        // given


        // when

        // then

    }

    @Test
    void 사용자_정보_수정(){
        log.debug("debug log= 사용자 정보 수정");

        // given


        // when

        // then

    }

    @Test
    void 문의_답변_등록(){
        log.debug("debug log= 문의 답변 등록");

        // given


        // when

        // then

    }

    @Test
    void 공지사항_등록(){
        log.debug("debug log= 공지사항 등록");

        // given


        // when

        // then

    }

    @Test
    void 공지사항_수정(){
        log.debug("debug log= 공지사항 수정");

        // given


        // when

        // then

    }

    @Test
    void FAQ_등록(){
        log.debug("debug log= FAQ 등록");

        // given


        // when

        // then

    }

    @Test
    void FAQ_수정(){
        log.debug("debug log= FAQ 수정");

        // given


        // when

        // then

    }




}
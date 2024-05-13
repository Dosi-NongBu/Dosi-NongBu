package MIN.DosiNongBu.controller.crop.dto.request;

import MIN.DosiNongBu.domain.user.UserCrop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCropSaveRequestDto {

    private Long userPlaceId;
    private String name;
    private String nickname;
    private Integer period;
    private Integer perPeriod;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer humidity;

    @Builder
    public UserCropSaveRequestDto(Long userId, Long userPlaceId, String name, String nickname, Integer period, Integer prePeriod, Integer maxTemperature, Integer minTemperature, Integer humidity) {
        this.userPlaceId = userPlaceId;
        this.name = name;
        this.nickname = nickname;
        this.period = period;
        this.perPeriod = prePeriod;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.humidity = humidity;
    }

    public UserCrop toEntity(){
        return UserCrop.builder()
                .name(name)
                .nickname(nickname)
                .period(period)
                .prePeriod(perPeriod)
                .maxTemperature(maxTemperature)
                .minTemperature(minTemperature)
                .humidity(humidity)
                .build();
    }
}

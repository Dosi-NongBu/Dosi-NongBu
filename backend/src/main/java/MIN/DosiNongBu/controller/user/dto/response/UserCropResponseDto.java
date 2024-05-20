package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.user.UserCrop;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

//내 작물 조회
@Getter
public class UserCropResponseDto {

    private String name;
    private String nickname;
    private List<String> imageUrls;
    private LocalDateTime startDate;
    private Integer period;
    private Integer prePeriod;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer humidity;

    public UserCropResponseDto(UserCrop entity) {
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.imageUrls = entity.getImageUrls();
        this.startDate = entity.getModifiedDate();
        this.period = entity.getPeriod();
        this.prePeriod = entity.getPrePeriod();
        this.maxTemperature = entity.getMaxTemperature();
        this.minTemperature = entity.getMinTemperature();
        this.humidity = entity.getHumidity();
    }
}

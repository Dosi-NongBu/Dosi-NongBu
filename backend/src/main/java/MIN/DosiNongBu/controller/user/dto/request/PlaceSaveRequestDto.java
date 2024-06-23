package MIN.DosiNongBu.controller.user.dto.request;

import MIN.DosiNongBu.aop.EnumValue;
import MIN.DosiNongBu.domain.user.UserPlace;
import MIN.DosiNongBu.domain.user.constant.DirectionType;
import MIN.DosiNongBu.domain.user.constant.LightType;
import MIN.DosiNongBu.domain.user.constant.PlaceType;
import MIN.DosiNongBu.domain.user.constant.QuantityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceSaveRequestDto {

    private String name;

    @EnumValue(enumClass = PlaceType.class, message = "장소가 잘못되었습니다.", ignoreCase = true)
    private String placeType;

    @EnumValue(enumClass = DirectionType.class, message = "방향이 잘못되었습니다.", ignoreCase = true)
    private String directionType;

    @EnumValue(enumClass = LightType.class, message = "광류가 잘못되었습니다.", ignoreCase = true)
    private String lightType;

    @EnumValue(enumClass = QuantityType.class, message = "광량가 잘못되었습니다.", ignoreCase = true)
    private String quantityType;

    @Builder
    public PlaceSaveRequestDto(String name, String placeType, String directionType, String lightType, String quantityType) {
        this.name = name;
        this.placeType = placeType;
        this.directionType = directionType;
        this.lightType = lightType;
        this.quantityType = quantityType;
    }

    public UserPlace toEntity(){
        return UserPlace.builder()
                .name(name)
                .placeType(PlaceType.valueOf(placeType.toUpperCase()))
                .directionType(DirectionType.valueOf(directionType.toUpperCase()))
                .lightType(LightType.valueOf(lightType.toUpperCase()))
                .quantityType(QuantityType.valueOf(quantityType.toUpperCase()))
                .build();
    }
}

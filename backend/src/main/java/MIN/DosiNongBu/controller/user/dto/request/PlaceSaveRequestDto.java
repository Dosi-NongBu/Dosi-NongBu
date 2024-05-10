package MIN.DosiNongBu.controller.user.dto.request;

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
    private PlaceType place;
    private DirectionType direction;
    private LightType light;
    private QuantityType quantity;

    @Builder
    public PlaceSaveRequestDto(String name, PlaceType place, DirectionType direction, LightType light, QuantityType quantity) {
        this.name = name;
        this.place = place;
        this.direction = direction;
        this.light = light;
        this.quantity = quantity;
    }

    public UserPlace toEntity(){
        return UserPlace.builder()
                .name(name)
                .place(place)
                .direction(direction)
                .light(light)
                .quantity(quantity)
                .build();
    }
}

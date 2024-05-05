package MIN.DosiNongBu.controller.user.dto;

import MIN.DosiNongBu.domain.user.UserPlace;
import lombok.Getter;

@Getter
public class PlaceListResponseDto {
    private String name;

    public PlaceListResponseDto(UserPlace entity) {
        this.name = entity.getName();
    }
}

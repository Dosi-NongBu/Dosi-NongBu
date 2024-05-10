package MIN.DosiNongBu.controller.user.dto.response;

import MIN.DosiNongBu.domain.user.UserPlace;
import lombok.Getter;

@Getter
public class PlaceListResponseDto {
    private Long id;
    private String name;

    public PlaceListResponseDto(UserPlace entity) {
        this.id = entity.getUserPlaceId();
        this.name = entity.getName();
    }
}

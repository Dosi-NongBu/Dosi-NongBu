package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.user.constant.DirectionType;
import MIN.DosiNongBu.domain.user.constant.LightType;
import MIN.DosiNongBu.domain.user.constant.PlaceType;
import MIN.DosiNongBu.domain.user.constant.QuantityType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Table(name = "USERS_PLACE")
@Entity
public class UserPlace {
    /* PK */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPlaceId;

    /* FK */
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /* 속성 */
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "placeType", nullable = false)
    private PlaceType place;

    @Enumerated(EnumType.STRING)
    @Column(name = "directionType", nullable = false)
    private DirectionType direction;

    @Enumerated(EnumType.STRING)
    @Column(name = "lightType", nullable = false)
    private LightType light;

    @Enumerated(EnumType.STRING)
    @Column(name = "Quantity", nullable = false)
    private QuantityType quantity;

    @Builder
    public UserPlace(String name, PlaceType place, DirectionType direction, LightType light, QuantityType quantity) {
        this.name = name;
        this.place = place;
        this.direction = direction;
        this.light = light;
        this.quantity = quantity;
    }
}

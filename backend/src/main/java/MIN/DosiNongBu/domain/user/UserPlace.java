package MIN.DosiNongBu.domain.user;

import MIN.DosiNongBu.domain.user.constant.DirectionType;
import MIN.DosiNongBu.domain.user.constant.LightType;
import MIN.DosiNongBu.domain.user.constant.PlaceType;
import MIN.DosiNongBu.domain.user.constant.QuantityType;
import jakarta.persistence.*;
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
    private PlaceType placeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "directionType", nullable = false)
    private DirectionType directionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "lightType", nullable = false)
    private LightType lightType;

    @Enumerated(EnumType.STRING)
    @Column(name = "quantityType", nullable = false)
    private QuantityType quantityType;

}

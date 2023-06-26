package fr.jpierrot.superbowlbackend.pojo.entities;

import fr.jpierrot.superbowlbackend.pojo.entities.compositeKey.UsersBetsKey;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class UsersBets {

    @EmbeddedId
    private UsersBetsKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("betId")
    @JoinColumn(name = "bet_id")
    Bet betslip;
}

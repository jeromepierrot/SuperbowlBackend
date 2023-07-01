/*
package fr.jpierrot.superbowlbackend.pojo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_usersbets_user"))
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("betId")
    @JoinColumn(name = "bet_id", foreignKey = @ForeignKey(name = "FK_usersbets_bet"))
    Bet betslip;
}
*/

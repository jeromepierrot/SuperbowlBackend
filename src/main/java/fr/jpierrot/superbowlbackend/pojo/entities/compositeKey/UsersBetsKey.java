package fr.jpierrot.superbowlbackend.pojo.entities.compositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class UsersBetsKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bet_id")
    private Long betId;
}

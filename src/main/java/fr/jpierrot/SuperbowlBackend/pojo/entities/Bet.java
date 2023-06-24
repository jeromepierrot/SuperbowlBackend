package fr.jpierrot.SuperbowlBackend.pojo.entities;

import fr.jpierrot.SuperbowlBackend.pojo.states.BetStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name= "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToMany
    @JoinTable(name = "users_bets",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bet_id"))
    private List<User> users;

    @Builder.Default
    @Column(name = "wager", nullable = false)
    private Float wager = 0.0f;

    @Column(nullable = false)
    private Float finalOdds;

    @Enumerated(EnumType.STRING)
    @Column(name ="bet_status", nullable = false)
    private BetStatus betStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "creation_date")
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "modification_date")
    private ZonedDateTime modifiedDate = ZonedDateTime.now();

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", match=" + match +
                ", wager=" + wager +
                ", finalOdds=" + finalOdds +
                ", betStatus=" + betStatus +
                '}';
    }
}

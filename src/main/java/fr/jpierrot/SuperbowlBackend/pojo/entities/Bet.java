package fr.jpierrot.SuperbowlBackend.pojo.entities;

import fr.jpierrot.SuperbowlBackend.pojo.states.BetStatus;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

    @Builder.Default
    @Column(name = "wager", nullable = false)
    private Float wager = 0.0f;

    @Column(nullable = false)
    private Float finalOdds;

    @Enumerated(EnumType.STRING)
    @Column(name ="bet_status", nullable = false)
    private BetStatus betStatus;


}

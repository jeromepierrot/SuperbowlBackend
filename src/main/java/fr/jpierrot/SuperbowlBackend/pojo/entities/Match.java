package fr.jpierrot.SuperbowlBackend.pojo.entities;

import fr.jpierrot.SuperbowlBackend.pojo.states.MatchStatus;
import fr.jpierrot.SuperbowlBackend.pojo.states.Weather;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "matchs")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_a_team_id")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_team_id")
    private Team teamB;

    @Column(name = "odds_A", nullable = false)
    private float oddsA;

    @Column(name = "odds_B", nullable = false)
    private float oddsB;

    @Column(name = "score_A", nullable = true)
    private Integer scoreA;

    @Column(name = "score_B", nullable = true)
    private Integer scoreB;

    @OneToMany(mappedBy = "match")
    private Set<Bet> bets;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Weather weather = Weather.WEATHER_UNKONWN;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.GAME_NOT_SCHEDULED;

    @Builder.Default
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = false;

    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime endDate;
}

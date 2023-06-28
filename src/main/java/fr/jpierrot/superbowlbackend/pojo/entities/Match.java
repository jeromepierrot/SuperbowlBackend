package fr.jpierrot.superbowlbackend.pojo.entities;

import fr.jpierrot.superbowlbackend.pojo.states.MatchStatus;
import fr.jpierrot.superbowlbackend.pojo.states.Weather;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Builder.Default
    @ManyToOne
    @JoinColumn(name = "team_a_id", foreignKey = @ForeignKey(name = "FK_matches_team_a"))
    private Team teamA = null;

    @Builder.Default
    @ManyToOne
    @JoinColumn(name = "team_b_id", foreignKey = @ForeignKey(name = "FK_matches_team_b"))
    private Team teamB = null;

    @Column(name = "odds_A", nullable = false)
    private float oddsA;

    @Column(name = "odds_B", nullable = false)
    private float oddsB;

    @Column(name = "score_A", nullable = true)
    private Integer scoreA;

    @Column(name = "score_B", nullable = true)
    private Integer scoreB;

/*    @OneToMany(mappedBy = "match")
    private Set<Bet> bets;*/

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Weather weather = Weather.WEATHER_UNKNOWN;

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
        return "Match{" +
                "teamA=" + teamA +
                ", teamB=" + teamB +
                ", oddsA=" + oddsA +
                ", oddsB=" + oddsB +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                ", weather=" + weather +
                ", status=" + status +
                ", startDate=" + startDate +
                '}';
    }
}

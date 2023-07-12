package fr.jpierrot.superbowlbackend.pojo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Match.class)
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
    private Float oddsA;

    @Column(name = "odds_B", nullable = false)
    private Float oddsB;

    @Column(name = "score_A")
    private Integer scoreA;

    @Column(name = "score_B")
    private Integer scoreB;

/*    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "match")
    @JsonBackReference
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

    public boolean addTeamA(Team team) {
        if( team != null && team != this.teamB ) {
            this.setTeamA(team);
            return true;
        }
        return false;
    }

    public void removeTeamA() {
            this.setTeamA(null);
    }

    public boolean addTeamB(Team team) {
        if( team != null && team != this.teamA ) {
            this.setTeamB(team);
            return true;
        }
        return false;
    }

    public void removeTeamB() {
        this.setTeamB(null);
    }

    public void setOddsA(Float oddsA) {
        this.oddsA = oddsA;
        calcOdds(this.oddsA, this.oddsB);
    }

    public void setOddsB(Float oddsB) {
        this.oddsB = oddsB;
        this.oddsA = calcOdds(this.oddsB, this.oddsA);
    }

    /**
     * Decimal odds / Cote décimale (Europe) :
     * Odds_ratio = 1 / probability
     * team_X_probability (%) = odds_team_X / (odds_team_X + odds_team_Y)
     * team_Y_probability (%) = odds_team_Y / (odds_team_X + odds_team_Y)
     * team_X_probability (%) = 1 - team_Y_probability
     * team_Y_probability (%) = 1 - team_X_probability
     * => odds_team_X = 1 - odds_team_Y / (odds_team_X + odds_team_Y)
     * => odds_team_Y = 1 - odds_team_X / (odds_team_X + odds_team_Y)
     * @param oddsX
     * @param oddsY
     * @return oddsX
     */
    public static Float calcOdds(Float oddsX, Float oddsY) {
        return (oddsX + oddsY) / oddsX;
    }

    /**
     * Compute the match result based on both score A and score B
     * @return the id of the winner team, null if result is tie or game not over
     */
    public Long isWinner() {
        if(this.status != MatchStatus.GAME_OVER){
            /* match is not over */
            return null;
        } else if (this.scoreA > this.scoreB) {
            /* winner is team A */
                return this.teamA.getId();
        } else if (this.scoreB > this.scoreA) {
            /* winner is team B */
            return this.teamB.getId();
        } else {
            /* tie */
            return null;
        }
    }


    // MATCH STATUS MANAGEMENT //
    public boolean isCancelled() {
        return this.status == MatchStatus.GAME_CANCELLED;
    }

    public boolean isNotStarted() {
        return this.status == MatchStatus.GAME_NOT_STARTED;
    }

    public boolean isStarted() {
        return this.status == MatchStatus.GAME_STARTED;
    }

    public boolean isOver() {
        return this.status == MatchStatus.GAME_OVER;
    }

    public void start() {
        Match.setValid(this);
        if( this.startDate.compareTo(ZonedDateTime.now()) < 0 &&
                this.status.equals(MatchStatus.GAME_NOT_STARTED) &&
                this.isEnabled) {
            this.setStatus(MatchStatus.GAME_STARTED);
        }

    }

    public void setEnabled(Boolean enabled){
        this.isEnabled = enabled;
    }

    public void close() {
        Match.setValid(this);
        if( this.endDate.compareTo(ZonedDateTime.now()) < 0 &&
                this.status.equals(MatchStatus.GAME_STARTED)) {
            setStatus(MatchStatus.GAME_OVER);
            setEndDate(ZonedDateTime.now());
            setIsEnabled(true);
        }
    }

    public void cancel() {
        setStatus(MatchStatus.GAME_CANCELLED);
        if(this.startDate != null) {
            setEndDate(this.startDate);
        } else {
            setStartDate(ZonedDateTime.now());
            setEndDate(ZonedDateTime.now());
        }
    }

    private boolean isValid() {
        switch(this.getStatus()) {
            case GAME_NOT_SCHEDULED, GAME_CANCELLED -> {
                return false;
            }
            case GAME_OVER, GAME_STARTED, GAME_NOT_STARTED -> {
                return true;
            }
            default -> { return false; }
        }
    }

    public boolean isScheduled() { return this.status == MatchStatus.GAME_NOT_STARTED; }



    /**
     * A match is valid if all below requirements are true :
     * - both teamA and teamB are set
     * - both oddsA and oddsB are set
     * - matchStatus is not set to 'GAME_CANCELLED' or 'GAME_NOT_SCHEDULED'
     * - weatherStatus is not NULL
     */
    public static void setValid(Match match) {
        if (
                match.status != MatchStatus.GAME_NOT_SCHEDULED &&
                match.status != MatchStatus.GAME_CANCELLED &&
                match.startDate != null &&
                match.endDate != null &&
                match.teamA != null &&
                match.teamB != null &&
                match.oddsA != null &&
                match.oddsB != null &&
                match.weather != null &&
                match.weather != Weather.WEATHER_UNKNOWN
        ) {
            /* Game is scheduled and not started yet, bets are still allowed */
            if ( match.endDate.compareTo(match.startDate) > 0 &&
                    match.startDate.compareTo(ZonedDateTime.now()) > 0){
                /* Game is scheduled and not started yet, bets are still allowed */
                match.setStatus(MatchStatus.GAME_NOT_STARTED);
                match.setIsEnabled(true);
            } else if ( match.endDate.compareTo(ZonedDateTime.now()) > 0 ) {
                /* Game is started, bets are not allowed anymore */
                match.setStatus(MatchStatus.GAME_STARTED);
                match.setIsEnabled(true);
            } else if(ZonedDateTime.now().compareTo(match.endDate) >= 0) {
                /* Game is either Over scheduled time or GAME is OVER
                * it will need a manual close using '.close()' method */
                match.setStatus(MatchStatus.GAME_STARTED);
                match.setIsEnabled(true);
            } else {
                match.setStatus(MatchStatus.GAME_NOT_SCHEDULED);
                match.setIsEnabled(false);
            }
        } else {
            match.setIsEnabled(false);
        }
    }

    /**
     * A match is scheduled (= GAME_NOT_STARTED) if all below requirements are true :
     * - startDate is not NULL
     * - endDate is not NULL
     * - endDate > startDate
     * - both teamA and teamB are set
     * - both oddsA and oddsB are set
     * - matchStatus is not set to 'GAME_CANCELLED' or 'GAME_STARTED' or 'GAME_OVER'
     * @return false if one of these conditions is false
     */
    public static void setScheduled(Match match) {
        if (
                match.status != MatchStatus.GAME_OVER &&
                match.status != MatchStatus.GAME_STARTED &&
                match.status != MatchStatus.GAME_CANCELLED &&
                match.startDate != null &&
                match.endDate != null &&
                match.teamA != null &&
                match.teamB != null &&
                match.oddsA != null &&
                match.oddsB != null
                ) {

                if ( match.endDate.compareTo(match.startDate) > 0 &&
                        match.startDate.compareTo(ZonedDateTime.now()) > 0) {
                    /* Game is scheduled and not started yet, bets are still allowed */
                    match.setStatus(MatchStatus.GAME_NOT_STARTED);
                    match.setIsEnabled(true);
                } else {
                    /* Game is NOT scheduled */
                    match.setStatus(MatchStatus.GAME_NOT_SCHEDULED);
                    match.setIsEnabled(false);
            }
        }
    }

    public static void setStatus(Match match, MatchStatus status) {
        switch(status) {
            case GAME_CANCELLED -> match.cancel();
            case GAME_NOT_SCHEDULED, GAME_NOT_STARTED -> setScheduled(match);
            case GAME_STARTED -> setValid(match);
            case GAME_OVER -> match.close();
            default -> setValid(match);
        }
    }
}

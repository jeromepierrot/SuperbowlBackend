package fr.jpierrot.superbowlbackend.pojo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.jpierrot.superbowlbackend.pojo.states.BetStatus;
import fr.jpierrot.superbowlbackend.pojo.states.MatchStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name= "bets")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Bet.class)
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", foreignKey = @ForeignKey(name = "FK_bets_match"))
    @JsonManagedReference
    private Match match;

    @Builder.Default
    @Column(name = "wager", nullable = false)
    private Float wager = 0.0f;

    @Column(nullable = false)
    private Float finalOdds;

    @ManyToOne
    @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "FK_bets_team"))
    @JsonManagedReference
    private Team betOnTeam;

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


    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "bets")
    @JsonBackReference
    private List<User> users = null;

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

    public void addUser(User newUser) {
        if(this.users == null) {
            this.users = new ArrayList<>();
        }
        this.users.add(newUser);
    }

    public boolean removeUser(User user) {
        if(this.users != null) {
            return this.users.remove(user);
        }
        return false;
    }

    /**
     * Attach a match to this bet ONLY IF no previous match already attached
     * AND the newMatch exists
     * @return either true if newMatch added, or false in any other case
     */
    public boolean addMatch(Match newMatch) {
        if(newMatch != null && this.match == null) {
            this.match = newMatch;
            return true;
        }
        return false;
    }

    /**
     * Detach a match from this bet
     * (DOES NOT delete the match itself, nor the bet)
     */
    public void removeMatch() { this.match = null; }

    /**
     * A bet is valid when :
     * - a match is attached to it
     * - a user is attached to it
     * - the wager is different from 0.0f
     * - the finalOdds are different from 0.0f
     * Even if valid, this function doesn't stands for the BET_STATUS
     */
    public boolean isValid() {
        return ( this.users != null &&
                 this.match != null &&
                 this.wager > 0.0f &&
                 this.finalOdds > 0.0f);
    }

    /**
     * Compute the gain of the bet for the match depending on the wager value
     * @return the total gain (without the wager itself which is not lost)
     */
    Float calcGain(Float wager) {
        Float winnerOdds;
        if ( this.match.isWinner() != null &&
                this.match.isWinner().equals(this.match.getTeamA().getId()) ) {
            /* the winner is Team A ! */
            winnerOdds = this.match.getOddsA();
        } else if ( this.match.isWinner() != null &&
                this.match.isWinner().equals(this.match.getTeamB().getId()) ) {
            /* the winner is Team B ! */
            winnerOdds = this.match.getOddsB();
        } else {
            /* tie */
            // TODO : rule to be checked up
            return 0.0f; /* no gain, no loss */
        }
        return (winnerOdds * wager) - wager; /* gain */
    }

    Float calcWinnerOdds() {
        if ( this.match.isWinner() != null &&
                this.match.isWinner().equals(this.match.getTeamA().getId()) ) {
            /* the winner is Team A ! */
            return this.match.getOddsA();
        } else if ( this.match.isWinner() != null &&
                this.match.isWinner().equals(this.match.getTeamB().getId()) ) {
            /* the winner is Team B ! */
            return this.match.getOddsB();
        } else {
            /* tie */
            // TODO : rule to be checked up
            return 0.0f; /* no gain, no loss */
        }
    }

    /**
     * Compute the loss of the bet for the match depending on the wager value
     * @return negative value equal to the total loss (which is the total wager amount)
     */
    Float calcLoss(Float wager) {
        return -wager;
    }

    /**
     *
     * @return the value of the gain (if positive) or the value of the loss (if negative),
     *  or '0.0' (= no win, no loss) if match result is tie, or cancelled, or if bet cancelled
     */
    Float gain() {
        if (this.match.getStatus() != MatchStatus.GAME_OVER) {
            return 0.0f;
        }
        if (betStatus == BetStatus.BET_CLOSED && this.match.isWinner() != null) {
            if(this.betOnTeam.getId().equals(this.match.isWinner())) {
                /* bet is a win */
                return calcGain(this.wager);
            } else {
                /* bet is a lost */
                return calcLoss(this.wager);
            }
        } else {
            /* bet has been cancelled */
            /* or match result is tie */
            /* no win, no loss*/
            return 0.0f;
        }
    }
}

package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Bet;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
    @Query("SELECT b FROM Bet b " +
            "JOIN UsersBets ub ON ub.id.betId = b.id " +
            "JOIN User u ON u.id = ub.id.userId " +
            "WHERE u=?1")
    List<Bet> findAllBetsByUser(User user);

    @Query("SELECT b FROM Bet b " +
            "JOIN UsersBets ub ON ub.id.betId = b.id " +
            "JOIN User u ON u.id = ub.id.userId " +
            "WHERE b.id=?1 AND u=?2")
    Bet findBetByIdAndByUser(Long betId, User user);

    List<Bet> findBetsByMatch_Id(Long match_id);

    @Query("SELECT b FROM Bet b " +
            "JOIN Match m ON m.id = b.match.id " +
            "JOIN User u ON u.id = b.id " +
            "WHERE b.id=?1 AND u=?2")
    List<Bet> findBetsByMatch_IdAndByUser(Long match_id, User user);
}

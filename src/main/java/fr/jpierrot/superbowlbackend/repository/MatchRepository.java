package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Match;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("SELECT m FROM Match m")
    List<Match> findAllMatches();

    @Query("SELECT m FROM Match m WHERE m.id = ?1")
    Match findMatchById(Long id);

    List<Match> findMatchesByCreatedDateIs(ZonedDateTime zonedDateTime);

    List<Match> findMatchesByTeamANameContaining(Team teamA);
    List<Match> findMatchesByTeamBNameContaining(Team teamB);
}

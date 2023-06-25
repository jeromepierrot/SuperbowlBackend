package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query("SELECT p FROM Player p")
    List<Player> findAllPlayers();

    @Query("SELECT p FROM Player p WHERE p.team.id = ?1")
    List<Player> findAllPlayersByTeamId(Long teamId);

    Player findPlayerById(Long id);
}

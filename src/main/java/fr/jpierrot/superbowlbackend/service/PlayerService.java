package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.entities.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();

    List<Player> getAllPlayersByTeamId(Long teamId);

    List<Player> getAllPlayersByTeamName(String teamName);

    Player getPlayerById(Long id);

}

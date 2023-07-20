package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.data.DataRegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.data.PlayerRegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.entities.Player;

import java.util.List;

public interface PlayerService {
    // PLAYERS MANAGEMENT SERVICES: Read/ReadAll //
    // For ALL

    List<Player> getAllPlayers();

    List<Player> getAllPlayersByTeamId(Long teamId);

    List<Player> getAllPlayersByTeamName(String teamName);

    Player getPlayerById(Long id);

    // PLAYERS MANAGEMENT SERVICES: Create/Update/Delete //
    // For ADMINS

    DataRegisterResponse createPlayer(PlayerRegisterRequest newPlayer);
}

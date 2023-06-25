package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.repository.PlayerRepository;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAllPlayers();
    }

    @Override
    public List<Player> getAllPlayersByTeamId(Long teamId) {
        return playerRepository.findAllPlayersByTeamId(teamId);
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findPlayerById(id);
    }
}

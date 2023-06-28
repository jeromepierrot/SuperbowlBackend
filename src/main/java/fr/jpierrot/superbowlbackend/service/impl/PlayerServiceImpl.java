package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.repository.PlayerRepository;
import fr.jpierrot.superbowlbackend.repository.TeamRepository;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAllPlayers();
    }

    @Override
    public List<Player> getAllPlayersByTeamId(Long teamId) {
        return playerRepository.findAllPlayersByTeamId(teamId);
    }

    @Override
    public List<Player> getAllPlayersByTeamName(String teamName) {
        List<Player> foundPlayers = new java.util.ArrayList<>(Collections.emptyList());
        List<Team> foundTeams = teamRepository.findTeamByName(teamName);


        if(!foundTeams.isEmpty()){
            for ( Team team :
                    foundTeams
            ) {
                foundPlayers.addAll(playerRepository.findPlayersByTeamName(team.getName()));
            }
            return foundPlayers;
        }

        return Collections.emptyList();
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findPlayerById(id);
    }


}

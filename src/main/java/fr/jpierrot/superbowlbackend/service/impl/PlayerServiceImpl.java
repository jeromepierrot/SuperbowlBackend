package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.data.DataRegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.data.PlayerRegisterRequest;
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

    @Override
    public DataRegisterResponse createPlayer(PlayerRegisterRequest newPlayer) {
        String responseBody = DataRegisterResponse.OK_201_CREATED;
        Player player = new Player();
        Team team = new Team();

        /* check if all required fields are there*/
        if(!newPlayer.hasRequiredFields()) {
            responseBody = ErrorResponse.ERROR_400_BAD_REQUEST;
            return DataRegisterResponse.builder()
                    .message(responseBody)
                    .build();
        }

        Player foundPlayerList = playerRepository.findPlayerByLastnameIs(newPlayer.getLastname());

        if (foundPlayerList != null) {
            /* check if the team already exists */
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
        } else {
            /* prepare team data before persisting data */
            player.setLastname(newPlayer.getLastname());
            player.setFirstname(newPlayer.getFirstname());
            player.setNumber(newPlayer.getNumber());

            /* check if the optional country is there */
            if(newPlayer.getTeam() != null
                    && teamRepository.findTeamByNameIs(newPlayer.getTeam().getName()) != null) {
                team = teamRepository.findTeamByNameIs(newPlayer.getTeam().getName());
                player.setTeam(team);
            } else {
                /* we are not creating any new team here
                if the name doesn't match an existing team name */
                player.setTeam(null);
            }

            /* insert into database */
            try {
                player = playerRepository.save(player);
                responseBody = RegisterResponse.OK_201_CREATED;
            } catch (Exception e) {
                responseBody = ErrorResponse.ERROR_400_BAD_REQUEST;
                System.out.println("-- save new player failed: " + e.getMessage());
            }
        }

        return DataRegisterResponse.builder()
                .message(responseBody)
                .build();
    }
}

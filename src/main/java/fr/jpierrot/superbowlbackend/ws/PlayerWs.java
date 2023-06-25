package fr.jpierrot.superbowlbackend.ws;


import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import fr.jpierrot.superbowlbackend.service.TeamService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_PLAYER)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "GET")
public class PlayerWs {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(path="/team", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> getAllPlayersFromTeam(@RequestParam String name) {
        Team currentTeam = teamService.getOneTeamByNameOrNull(name);

        if(currentTeam != null) {
            return playerService.getAllPlayersByTeamId(currentTeam.getId());
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping(path="/{id}")
    public Player getPlayerById(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }
}

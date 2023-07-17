package fr.jpierrot.superbowlbackend.ws;


import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_PLAYER)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"GET"})
public class PlayerWs {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    Player getPlayerById(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }

/*    @GetMapping("/team")
    List<Player> getPlayersForTeamByTeamId(@RequestParam(name = "id") Long teamId) {
        return playerService.getAllPlayersByTeamId(teamId);
    }*/

    @GetMapping("/team")
    List<Player> getPlayersForTeamByTeamName(
            @RequestParam(name= "id", required = false) Long teamId,
            @RequestParam(name = "name", required = false) String teamName) {

            if(teamId != null && teamName == null) {
                return playerService.getAllPlayersByTeamId(teamId);
            }
            if (teamName != null && teamId == null) {
                return playerService.getAllPlayersByTeamName(teamName);
            }
            return Collections.emptyList();
    }
}

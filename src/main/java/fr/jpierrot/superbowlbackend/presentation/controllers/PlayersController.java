package fr.jpierrot.superbowlbackend.presentation.controllers;

import fr.jpierrot.superbowlbackend.pojo.data.PlayerRegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import fr.jpierrot.superbowlbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(MvcRegistration.ROOT) // (default Route => /admin27864 should redirect to /admin27864)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"GET"})
public class PlayersController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping(path = MvcRegistration.PLAYERS)
    public ModelAndView showPlayerList() {

        String viewName = "html/player-list";

        Map<String, Object> model = new HashMap<>();

        List<Player> playerList = playerService.getAllPlayers();

        if (playerList == null) {
            model.put("playerList", null);
        } else {
            model.put("playerList", playerList);
        }

        return new ModelAndView(viewName, model);
    }

    @GetMapping(path = MvcRegistration.PLAYERS + "/new")
    public ModelAndView newPlayerForm() {
        String viewName = "html/new-player";
        Player newPlayer = new Player();
        Map<String, Object> model = new HashMap<>();
        List<Team> teamList = teamService.getAllTeams();

        model.put("newPlayer", newPlayer);

        if (teamList == null) {
            model.put("teamList", null);
        } else {
            model.put("teamList", teamList);
        }

        return new ModelAndView(viewName, model);
    }


    @PostMapping(path = MvcRegistration.PLAYERS + "/new")
    public ModelAndView submitNewPlayerForm(PlayerRegisterRequest newPlayer) {
        playerService.createPlayer(newPlayer);

        RedirectView redirect = new RedirectView();
        redirect.setUrl(MvcRegistration.ROOT + MvcRegistration.PLAYERS);

        return new ModelAndView(redirect);
    }
}
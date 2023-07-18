package fr.jpierrot.superbowlbackend.presentation.controllers;

import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(MvcRegistration.ROOT) // (default Route => /admin27864 should redirect to /admin27864)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"GET"})
public class PlayersController {
    @Autowired
    private PlayerService playerService;

    @GetMapping(path = MvcRegistration.PLAYERS)
    public ModelAndView showTeamList() {

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
}
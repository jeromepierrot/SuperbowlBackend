package fr.jpierrot.superbowlbackend.presentation.controllers;

import fr.jpierrot.superbowlbackend.pojo.data.TeamRegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.entities.Country;
import fr.jpierrot.superbowlbackend.pojo.entities.Player;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.service.CountryService;
import fr.jpierrot.superbowlbackend.service.PlayerService;
import fr.jpierrot.superbowlbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(MvcRegistration.ROOT) // (default Route => /admin27864 should redirect to /admin27864)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"*"})
public class TeamsController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CountryService countryService;

    @GetMapping(path = MvcRegistration.TEAMS)
    public ModelAndView showTeamList() {

        String viewName = "html/team-list";

        Map<String, Object> model = new HashMap<>();

        List<Team> teamList = teamService.getAllTeams();

        if (teamList == null) {
            model.put("teamList", null);
        } else {
            model.put("teamList", teamList);
        }

        return new ModelAndView(viewName, model);
    }

    @GetMapping(path = MvcRegistration.TEAMS + "/new")
    public ModelAndView newTeamForm() {
        String viewName = "html/new-team";

        Map<String, Object> model = new HashMap<>();

        List<Player> playerList = playerService.getAllPlayers();
        List<Country> countryList = countryService.getAllCountries();
        Team newTeam = new Team();

        model.put("newTeam", newTeam);

        if (playerList == null) {
            model.put("playerList", null);
        } else {
            model.put("playerList", playerList);
        }

        if (countryList == null) {
            model.put("countryList", null);
        } else {
            model.put("countryList", countryList);
        }

        return new ModelAndView(viewName, model);
    }

    @PostMapping(path = MvcRegistration.TEAMS + "/new")
    public ModelAndView submitNewTeamForm(TeamRegisterRequest newTeam) {
        teamService.createTeam(newTeam);

        RedirectView redirect = new RedirectView();
        redirect.setUrl(MvcRegistration.ROOT + MvcRegistration.TEAMS);

        return new ModelAndView(redirect);
/*        return getRegisterResponse(newTeam, createTeamResponse, "createTeam");*/

    }
}

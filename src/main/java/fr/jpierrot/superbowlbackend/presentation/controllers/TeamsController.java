package fr.jpierrot.superbowlbackend.presentation.controllers;

import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.service.TeamService;
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
public class TeamsController {
    @Autowired
    private TeamService teamService;

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
}

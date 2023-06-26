package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.entities.Match;
import fr.jpierrot.superbowlbackend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_MATCH)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"GET"})
public class MatchWs {
    @Autowired
    private MatchService matchService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Match getMatchById(@PathVariable("id") Long id) {
        return matchService.getMatchById(id);
    }

    @GetMapping(path = "/team", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Match> getMatchByTeamName(@RequestParam(name = "name", required = false) String teamAName,
                                          @RequestParam(name = "id", required = false) Long id) {
        List<Match> foundMatches;
        if(id != null &&  teamAName == null) {
            foundMatches = matchService.getMatchByTeamAId(id);
        } else if (teamAName != null) {
            foundMatches = matchService.getMatchByTeamName(teamAName);
        } else {
            foundMatches = Collections.emptyList();
        }

        return foundMatches;
    }
}

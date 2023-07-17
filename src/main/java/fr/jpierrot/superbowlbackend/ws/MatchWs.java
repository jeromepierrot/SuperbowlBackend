package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.entities.Match;
import fr.jpierrot.superbowlbackend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_MATCH)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"*"})
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

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Match> getMatchByDate(@RequestParam(name = "date", required = false) String date) {

        date = date + "T00:00:00+02:00";
        System.out.println("--- Get Request parameter : " + date);
        ZonedDateTime zdt = ZonedDateTime.parse(date, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println("-- Get Request: " + date + " converted to ZDT : " + zdt);
        return matchService.getMatchByStartDate(zdt);
    }

    @GetMapping(path = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Match> getMatchForToday() {
        ZonedDateTime today = ZonedDateTime.now()
                .withZoneSameInstant(ZoneId.of("UTC"))
                .with(LocalTime.of(0,0));
        System.out.println("-- Get Request all match for this date: " + today);

        return matchService.getMatchByStartDate(today);
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

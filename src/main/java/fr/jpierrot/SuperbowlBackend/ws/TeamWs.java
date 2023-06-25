package fr.jpierrot.SuperbowlBackend.ws;

import fr.jpierrot.SuperbowlBackend.pojo.auth.RegisterResponse;
import fr.jpierrot.SuperbowlBackend.pojo.entities.Team;
import fr.jpierrot.SuperbowlBackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_TEAM)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"GET", "POST"})
public class TeamWs {

    @Autowired
    private TeamService teamService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getAllTeams() {

        return teamService.getAllTeams();
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Team getTeamById(@PathVariable("id") Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeamByName(@PathVariable("name") String name) {
        return teamService.getTeamByName(name);
    }

    @GetMapping(path = "/country/id/{countryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeamByCountryId(@PathVariable("countryId") Long id) {
        return teamService.getTeamByCountryId(id);
    }

    @GetMapping(path = "/country/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeamByCountryName(@PathVariable("countryName") String countryName) {

        return teamService.getTeamByCountryName(countryName);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> createTeam(@RequestBody Team newTeam) {
        RegisterResponse createTeamResponse = teamService.createTeam(newTeam);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ApiRegistration.API_REST+ApiRegistration.API_TEAM+"/{id}")
                .buildAndExpand(newTeam.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", "createTeam");

        return ResponseEntity.created(location).header(responseHeaders.toString()).body(createTeamResponse);
    }
}

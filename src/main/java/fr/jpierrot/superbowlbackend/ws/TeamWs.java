package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Team getTeamByIdOrByName(
            @RequestParam(name= "id", required = false) Long id,
            @RequestParam(name = "name", required = false) String name) {

        if(id != null && name == null) {
            return teamService.getTeamById(id);
        }
        if (name != null && id == null) {
            return teamService.getOneTeamByNameOrNull(name);
        }
        return null;
    }

    @GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Team> searchTeamsByNameOrCountry(
            @RequestParam(name= "id", required = false) Long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "country_id", required = false) Long countryId,
            @RequestParam(name = "country", required = false) String countryName) {
        List<Team> foundTeams = new ArrayList<>();
        if(countryId == null && countryName == null) {
            if(id != null && name == null) {
                foundTeams.add(teamService.getTeamById(id));
            } else if (name != null && id == null) {
                foundTeams = teamService.getTeamsByName(name);
            } else {
                foundTeams = Collections.emptyList();
            }
        } else if (countryId != null && countryName == null) {
            if(id != null && name == null) {
                foundTeams = teamService.getTeamByIdAndCountryId(id, countryId);
            } else if (name != null && id == null) {
                foundTeams = teamService.getTeamsByNameAndCountryId(name, countryId);
            } else {
                foundTeams = Collections.emptyList();
            }
        } else /*(countryName != null)*/ {
            if(id != null && name == null) {
                foundTeams = (teamService.getTeamByIdAndCountryName(id, countryName));
            } else if (name != null && id == null) {
                foundTeams = teamService.getTeamsByNameAndCountryName(name, countryName);
            } else {
                foundTeams = Collections.emptyList();
            }
        }

        return foundTeams;
    }

    @GetMapping(path = "/country/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeamByCountryId(@RequestParam(name = "id") Long id) {
        return teamService.getTeamByCountryId(id);
    }

    @GetMapping(path = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Team> getTeamByCountryName(
            @RequestParam(name= "id", required = false) Long id,
            @RequestParam(name = "name", required = false) String name) {
        if(id != null && name == null) {
            return teamService.getTeamByCountryId(id);
        } else if (name != null && id == null) {
            return teamService.getTeamByCountryName(name);
        } else {
            return Collections.emptyList();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> createTeam(@RequestBody Team newTeam) {
        RegisterResponse createTeamResponse = teamService.createTeam(newTeam);

        return getRegisterResponse(newTeam, createTeamResponse, "createTeam");
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateTeamById(@RequestBody Team teamToUpdate, @PathVariable("id") Long id) {

        RegisterResponse updateTeamResponse = teamService.updateTeamById(teamToUpdate, id);

        return getRegisterResponse(teamToUpdate, updateTeamResponse, "updateTeam");
    }

    @PutMapping(path="/name/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateTeamByName(@RequestBody Team teamToUpdate, @PathVariable("name") String teamName) {

        RegisterResponse updateTeamResponse = teamService.updateTeamByName(teamToUpdate, teamName);

        return getRegisterResponse(teamToUpdate, updateTeamResponse, "updateTeam");
    }

    // TODO: updateTeamByIdWithCountryId - NOT TESTED
    /*@PutMapping(path="/id={teamId}&country_id={countryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateTeamByIdWithCountryId(
            @RequestBody Team teamToUpdate,
            @PathVariable("teamId") Long teamId,
            @PathVariable("countryId") Long countryId) {
        RegisterResponse updateTeamResponse = teamService.updateTeamByIdWithCountryId(teamToUpdate, teamId, countryId);

        return getRegisterResponse(teamToUpdate, updateTeamResponse, "updateTeam");

    }*/

    /* Utility methods */

    /**
     * This method help building HTTP Response based on RegisterResponse returned by the TeamService
     */
    private ResponseEntity<RegisterResponse> getRegisterResponse(@RequestBody Team teamToUpdate, RegisterResponse updateTeamResponse, String responseHeader) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ApiRegistration.API_REST+ApiRegistration.API_TEAM+"/{id}")
                .buildAndExpand(teamToUpdate.getId())
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", responseHeader);

        return ResponseEntity.created(location).header(responseHeaders.toString()).body(updateTeamResponse);
    }
}

package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.data.DataRegisterResponse;
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
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"GET"})
public class TeamWs {

    @Autowired
    private TeamService teamService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Team getTeamById(@PathVariable("id") Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping(path="/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Team getTeamByName(
            @PathVariable("name") String name) {
            return teamService.getOneTeamByNameOrNull(name);
    }

    @GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Team> searchTeamsByNameOrByCountry(
            @RequestParam(name= "team_id", required = false) Long teamId,
            @RequestParam(name = "team_name", required = false) String teamName,
            @RequestParam(name = "country_id", required = false) Long countryId,
            @RequestParam(name = "country_name", required = false) String countryName) {

        List<Team> foundTeams = new ArrayList<>();
        if(countryId == null && countryName == null) {
            if(teamId != null && teamName == null) {
                foundTeams.add(teamService.getTeamById(teamId));
            } else if (teamName != null && teamId == null) {
                foundTeams = teamService.getTeamsByName(teamName);
            } else {
                /* We don't process if 2 parameters ore more */
                foundTeams = Collections.emptyList();
            }
        } else if (countryId != null && countryName == null) { /*(countryId != null)*/
            if(teamId == null && teamName == null) {
                foundTeams = teamService.getTeamByCountryId(countryId);
            } else {
                /* We don't process if 2 parameters ore more */
                foundTeams = Collections.emptyList();
            }
        } else /*(countryName != null)*/ {
            if(teamId == null && teamName == null) {
                foundTeams = teamService.getTeamByCountryName(countryName);
            } else {
                /* We don't process if 2 parameters ore more */
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
            @RequestParam(name= "country_id", required = false) Long countryId,
            @RequestParam(name = "country_name", required = false) String countryName) {
        if(countryId != null && countryName == null) {
            return teamService.getTeamByCountryId(countryId);
        } else if (countryName != null && countryId == null) {
            return teamService.getTeamByCountryName(countryName);
        } else {
            return Collections.emptyList();
        }
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataRegisterResponse> updateTeamById(@RequestBody Team teamToUpdate, @PathVariable("id") Long id) {

        DataRegisterResponse updateTeamResponse = teamService.updateTeamById(teamToUpdate, id);

        return getRegisterResponse(teamToUpdate, updateTeamResponse, "updateTeam");
    }

    @PutMapping(path="/name/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataRegisterResponse> updateTeamByName(@RequestBody Team teamToUpdate, @PathVariable("name") String teamName) {

        DataRegisterResponse updateTeamResponse = teamService.updateTeamByName(teamToUpdate, teamName);

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
    private ResponseEntity<DataRegisterResponse> getRegisterResponse(@RequestBody Team teamToUpdate, DataRegisterResponse updateTeamResponse, String responseHeader) {
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

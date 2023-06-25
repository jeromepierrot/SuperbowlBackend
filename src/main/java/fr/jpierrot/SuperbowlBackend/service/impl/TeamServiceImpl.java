package fr.jpierrot.SuperbowlBackend.service.impl;

import fr.jpierrot.SuperbowlBackend.pojo.auth.ErrorResponse;
import fr.jpierrot.SuperbowlBackend.pojo.auth.RegisterResponse;
import fr.jpierrot.SuperbowlBackend.pojo.entities.Team;
import fr.jpierrot.SuperbowlBackend.repository.CountryRepository;
import fr.jpierrot.SuperbowlBackend.repository.TeamRepository;
import fr.jpierrot.SuperbowlBackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAllTeams();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findTeamById(id);
    }

    @Override
    public List<Team> getTeamByName(String teamName) {
        return teamRepository.findTeamByName(teamName);
    }

    @Override
    public List<Team> getTeamByCountryId(Long id) {
        return teamRepository.findTeamByCountryId(id);
    }

    @Override
    public List<Team> getTeamByCountryName(String countryName) {
        return teamRepository.findTeamByCountryName(countryName);
    }

    @Override
    public RegisterResponse createTeam(Team newTeam) {
        /* insert into database */
        teamRepository.save(newTeam);

        String responseBody = RegisterResponse.OK_201_CREATED;

        return RegisterResponse.builder()
                .body(responseBody)
                .build();
    }

    @Override
    public RegisterResponse updateTeamById(Team team, Long id) {
        if(team.getCountry() != null) {
            return updateTeamByIdWithCountryId(team, id, team.getCountry().getId());
        } else {
            return updateTeamByIdWithCountryId(team, id, null);
        }
    }

    @Override
    public RegisterResponse updateTeamByIdWithCountryId(Team team, Long teamId, Long countryId) {
        Team teamToUpdate;
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
        if(teamRepository.existsById(teamId)){
            teamToUpdate = teamRepository.findTeamById(teamId);

            this.updateTeamWithNewCountryId(teamToUpdate, team.getName(), countryId);

            /* update into database */
            teamRepository.save(teamToUpdate);
            responseBody = RegisterResponse.OK_201_UPDATED;
            }

        return RegisterResponse.builder()
                .body(responseBody)
                .build();
    }

    @Override
    public RegisterResponse updateTeamByName(Team team, String oldTeamName) {
        if(team.getCountry() != null) {
            return updateTeamByNameWithCountryId(team, oldTeamName, team.getCountry().getId());
        } else {
            return updateTeamByNameWithCountryId(team, oldTeamName, null);
        }
    }

    @Override
    public RegisterResponse updateTeamByNameWithCountryId(Team team, String oldTeamName, Long countryId) {
        Team teamToUpdate;
        List<Team> responseTeams = teamRepository.findTeamByName(oldTeamName);
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;

        if(responseTeams != null && responseTeams.size() == 1) {
            teamToUpdate = responseTeams.get(0);

            this.updateTeamWithNewCountryId(teamToUpdate, team.getName(), countryId);

            /* update into database */
            teamRepository.save(teamToUpdate);
            responseBody = RegisterResponse.OK_201_UPDATED;
        } else if (responseTeams != null && responseTeams.size() > 1) {
            /* Multiple resources: cannot identify the correct one, and so, cannot process the request */
            responseBody = ErrorResponse.ERROR_405_NOT_ALLOWED;
        }

        return RegisterResponse.builder()
                .body(responseBody)
                .build();
    }

    /* Utility methods */
    public void updateTeamWithNewCountryId(Team teamToUpdate, String newTeamName, Long newCountryId) {
        teamToUpdate.setName(newTeamName);

        if(newCountryId!=null && countryRepository.existsById(newCountryId)) {
            teamToUpdate.setCountry(countryRepository.findCountryById(newCountryId));
        } else {
            teamToUpdate.setCountry(null);
        }
    }

}

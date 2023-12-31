package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.data.DataRegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.data.TeamRegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.entities.Country;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.repository.CountryRepository;
import fr.jpierrot.superbowlbackend.repository.TeamRepository;
import fr.jpierrot.superbowlbackend.service.TeamService;
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

    /**
     * Carreful: this method, without a 's' after 'team', returns one single team (or null if found many)
     * If need to get (potential) multiple teams, like searching purposes, please use getTeamsByName(searchName), 'teams' with an 's'
     * */
    @Override
    public Team getOneTeamByNameOrNull(String teamName) {
        List<Team> foundTeams = teamRepository.findTeamByName(teamName);
        if (foundTeams == null || foundTeams.isEmpty()) {
            return null;
        }

        if (foundTeams.size() > 1) {
            return null;
        }

        return foundTeams.get(0);
    }

    /**
     * Careful: this method, with a 's' after 'team', returns a list of teams for searching purposes
     * If you need to find one single team, please use getOneTeamByNameOrNull(teamName), 'team' without 's'
     */
    public List<Team> getTeamsByName(String searchTeamName) {
        return teamRepository.findTeamByName(searchTeamName);
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
    public DataRegisterResponse createTeam(TeamRegisterRequest newTeam) {
        String responseBody = DataRegisterResponse.OK_201_CREATED;
        Team team = new Team();
        Country country = new Country();

        /* check if all required fields are there*/
        if(!newTeam.hasRequiredFields()) {
            responseBody = ErrorResponse.ERROR_400_BAD_REQUEST;
            return DataRegisterResponse.builder()
                    .message(responseBody)
                    .build();
        }

        List<Team> foundTeamList = teamRepository.findTeamByName(newTeam.getName());

        if (!foundTeamList.isEmpty()) {
            /* check if the team already exists */
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
        } else {
            /* prepare team data before persisting data */
            team.setName(newTeam.getName());

            /* check if the optional country field is there */
            if(newTeam.getCountry() != null
                    && countryRepository.findCountryByNameIs(newTeam.getCountry().getName()) != null
            ) {
                country = countryRepository.findCountryByNameIs(newTeam.getCountry().getName());
                team.setCountry(country);
            } else {
                try {
                    country = countryRepository.save(newTeam.getCountry());
                } catch (Exception e) {
                    country = null;
                    System.out.println("-- save new country failed: " + e.getMessage());
                } finally {
                    team.setCountry(country);
                }
            }

            /* insert into database */
            try {
                teamRepository.save(team);
                responseBody = RegisterResponse.OK_201_CREATED;
            } catch (Exception e) {
                responseBody = ErrorResponse.ERROR_400_BAD_REQUEST;
                System.out.println("-- save new team failed: " + e.getMessage());
            }
        }

        return DataRegisterResponse.builder()
                .message(responseBody)
                .build();
    }

    @Override
    public DataRegisterResponse updateTeamById(Team team, Long id) {
        if(team.getCountry() != null) {
            return updateTeamByIdWithCountryId(team, id, team.getCountry().getId());
        } else {
            return updateTeamByIdWithCountryId(team, id, null);
        }
    }

    @Override
    public DataRegisterResponse updateTeamByIdWithCountryId(Team team, Long teamId, Long countryId) {
        Team teamToUpdate;
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
        if(teamRepository.existsById(teamId)){
            teamToUpdate = teamRepository.findTeamById(teamId);

            this.updateTeamWithNewCountryId(teamToUpdate, team.getName(), countryId);

            /* update into database */
            teamRepository.save(teamToUpdate);
            responseBody = RegisterResponse.OK_201_UPDATED;
            }

        return DataRegisterResponse.builder()
                .message(responseBody)
                .build();
    }

    @Override
    public DataRegisterResponse updateTeamByName(Team team, String oldTeamName) {
        if(team.getCountry() != null) {
            return updateTeamByNameWithCountryId(team, oldTeamName, team.getCountry().getId());
        } else {
            return updateTeamByNameWithCountryId(team, oldTeamName, null);
        }
    }

    @Override
    public DataRegisterResponse updateTeamByNameWithCountryId(Team team, String oldTeamName, Long countryId) {
        Team teamToUpdate;
        List<Team> foundTeams = teamRepository.findTeamByName(oldTeamName);
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;

        if(foundTeams != null && foundTeams.size() == 1) {
            teamToUpdate = foundTeams.get(0);

            this.updateTeamWithNewCountryId(teamToUpdate, team.getName(), countryId);

            /* update into database */
            teamRepository.save(teamToUpdate);
            responseBody = DataRegisterResponse.OK_201_UPDATED;
        } else if (foundTeams != null && foundTeams.size() > 1) {
            /* Multiple resources: cannot identify the correct one, and so, cannot process the request */
            responseBody = ErrorResponse.ERROR_405_NOT_ALLOWED;
        }

        return DataRegisterResponse.builder()
                .message(responseBody)
                .build();
    }

    @Override
    public List<Team> getTeamByIdAndCountryId(Long id, Long countryId) {
        return teamRepository.findTeamsByIdAndCountryIdOrderByCountry(id, countryId);
    }

    @Override
    public List<Team> getTeamsByNameAndCountryId(String teamName, Long countryId) {
        return teamRepository.findTeamsByNameContainingAndCountryIdOrderByName(teamName, countryId);
    }

    @Override
    public List<Team> getTeamByIdAndCountryName(Long id, String countryName) {
        return teamRepository.findTeamsByIdAndCountryNameContainingOrderByCountry(id, countryName);
    }

    @Override
    public List<Team> getTeamsByNameAndCountryName(String teamName, String countryName) {
        return teamRepository.findTeamsByNameContainingAndCountryNameContainingOrderByName(teamName, countryName);
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

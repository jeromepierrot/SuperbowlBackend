package fr.jpierrot.SuperbowlBackend.service.impl;

import fr.jpierrot.SuperbowlBackend.pojo.auth.RegisterResponse;
import fr.jpierrot.SuperbowlBackend.pojo.entities.Team;
import fr.jpierrot.SuperbowlBackend.repository.TeamRepository;
import fr.jpierrot.SuperbowlBackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

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
    public RegisterResponse updateTeamById(Team user, Long id) {

        return null;
    }

/*    @Override
    public RegisterResponse updateTeamByName(Team user, String teamName) {
        return null;
    }*/
}

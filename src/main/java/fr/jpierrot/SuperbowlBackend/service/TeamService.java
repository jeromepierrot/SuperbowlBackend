package fr.jpierrot.SuperbowlBackend.service;

import fr.jpierrot.SuperbowlBackend.pojo.auth.RegisterResponse;
import fr.jpierrot.SuperbowlBackend.pojo.entities.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();

    Team getTeamById(Long id);

    List<Team> getTeamByName(String teamName);

    List<Team> getTeamByCountryId(Long id);
    List<Team> getTeamByCountryName(String Name);

    RegisterResponse createTeam(Team newnewTeam);

    RegisterResponse updateTeamById(Team user, Long id);
/*    RegisterResponse updateTeamByName(Team user, String teamName);*/
}

package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();

    Team getTeamById(Long id);

    Team getOneTeamByNameOrNull(String teamName);

    List<Team> getTeamsByName(String searchTeamName);

    List<Team> getTeamByCountryId(Long id);
    List<Team> getTeamByCountryName(String name);

    RegisterResponse createTeam(Team newnewTeam);

    RegisterResponse updateTeamById(Team team, Long id);

    RegisterResponse updateTeamByIdWithCountryId(Team team, Long teamId, Long countryId);

    RegisterResponse updateTeamByName(Team user, String teamName);

    RegisterResponse updateTeamByNameWithCountryId(Team team, String oldTeamName, Long countryId);

    List<Team> getTeamByIdAndCountryId(Long id, Long countryId);

    List<Team> getTeamsByNameAndCountryId(String name, Long countryId);

    List<Team> getTeamByIdAndCountryName(Long id, String countryName);

    List<Team> getTeamsByNameAndCountryName(String name, String countryName);
}

package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.data.DataRegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.data.TeamRegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;

import java.util.List;

public interface TeamService {

    // TEAMS MANAGEMENT SERVICES: Read/ReadAll //
    // For ALL

    List<Team> getAllTeams();

    Team getTeamById(Long id);

    Team getOneTeamByNameOrNull(String teamName);

    List<Team> getTeamsByName(String searchTeamName);

    List<Team> getTeamByCountryId(Long id);

    List<Team> getTeamByCountryName(String name);

    List<Team> getTeamByIdAndCountryId(Long id, Long countryId);

    List<Team> getTeamsByNameAndCountryId(String name, Long countryId);

    List<Team> getTeamByIdAndCountryName(Long id, String countryName);

    List<Team> getTeamsByNameAndCountryName(String name, String countryName);

    // TEAMS MANAGEMENT SERVICES: Create/Update/Delete //
    // For ADMINS

    DataRegisterResponse createTeam(TeamRegisterRequest newTeam);

    DataRegisterResponse updateTeamById(Team team, Long id);

    DataRegisterResponse updateTeamByIdWithCountryId(Team team, Long teamId, Long countryId);

    DataRegisterResponse updateTeamByName(Team user, String teamName);

    DataRegisterResponse updateTeamByNameWithCountryId(Team team, String oldTeamName, Long countryId);

}

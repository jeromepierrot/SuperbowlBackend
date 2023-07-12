package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.entities.Match;

import java.time.ZonedDateTime;
import java.util.List;

public interface MatchService {
    // MATCHES MANAGEMENT SERVICES: Read/ReadAll //
    // For ALL
    List<Match> getAllMatches();

    Match getMatchById(Long id);

    List<Match> getMatchByTeamName(String teamAName);

    List<Match> getMatchByTeamAId(Long teamAId);

    List<Match> getMatchByStartDate(ZonedDateTime startZonedDateTime);

    // MATCHES MANAGEMENT SERVICES: Create/Update/Delete //
    // For ADMINS
}

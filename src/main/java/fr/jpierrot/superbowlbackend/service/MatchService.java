package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.entities.Match;

import java.time.ZonedDateTime;
import java.util.List;

public interface MatchService {
    List<Match> getAllMatches();

    Match getMatchById(Long id);

    List<Match> getMatchByTeamName(String teamAName);
    List<Match> getMatchByTeamAId(Long teamAId);


    List<Match> getMatchByStartDate(ZonedDateTime startZonedDateTime);
}

package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.entities.Match;
import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import fr.jpierrot.superbowlbackend.repository.MatchRepository;
import fr.jpierrot.superbowlbackend.repository.TeamRepository;
import fr.jpierrot.superbowlbackend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAllMatches();
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findMatchById(id);
    }

    @Override
    public List<Match> getMatchByTeamName(String teamAName) {
        List<Team> foundTeam = teamRepository.findTeamByName(teamAName);
        List<Match> foundMatches = new ArrayList<>();

        if (foundTeam.isEmpty()) {
            return Collections.emptyList();
        }


        for (Team ta : foundTeam
             ) {
            try {
                foundMatches.addAll(matchRepository.findMatchesByTeamANameContaining(ta));
            } catch (Exception e) {
                System.out.printf("Erreur de la recherche Team A: "+ e.getMessage());
            }
        }

        for (Team tb : foundTeam
        ) {
            try {
                foundMatches.addAll(matchRepository.findMatchesByTeamBNameContaining(tb));
            } catch (Exception e) {
                System.out.printf("Erreur lors de la recherche Team B: " + e.getMessage());
            }
        }

        return foundMatches;
    }

    @Override
    public List<Match> getMatchByTeamAId(Long teamAId) {
        // TODO: getMatchByTeamAId
        return null;
    }

    @Override
    public List<Match> getMatchByStartDate(ZonedDateTime startZonedDateTime) {

        System.out.println("-- Get Request all match for timestamp:" + startZonedDateTime);
        System.out.println("-- Get Request all match for timestamp:" + startZonedDateTime.plusDays(1));

        return matchRepository.findMatchesByStartDateBetween(startZonedDateTime, startZonedDateTime.plusDays(1));
    }
}

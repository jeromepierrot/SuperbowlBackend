package fr.jpierrot.superbowlbackend.service;


import fr.jpierrot.superbowlbackend.pojo.entities.Bet;

import java.util.List;

public interface BetService {

    List<Bet> getAllBetByUserId(Long userId);

    List<Bet> getBetByUserIdAndByMatchId(Long userId, Long matchId);

    Bet getBetByIdAndByUserId(Long betId, Long userId);
}

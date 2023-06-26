package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.entities.Bet;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import fr.jpierrot.superbowlbackend.repository.BetRepository;
import fr.jpierrot.superbowlbackend.repository.UserRepository;
import fr.jpierrot.superbowlbackend.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Bet> getAllBetByUserId(Long userId) {
        User currentUser;
        List<Bet> currentBets = new ArrayList<>();

        if(userRepository.existsById(userId)) {
            currentUser = userRepository.findUserById(userId);
            // TODO : verfier le rôle de l'utilisateur
            // Si ROLE_USER -> poursuivre
            // Si ROLE_ADMIN ou ROLE_COMMENTATOR -> lancer une exception 'OperationNotAllowedException' et/ou 'BadRoleException'
            currentBets = betRepository.findAllBetsByUser(currentUser);
        }

        return currentBets;
    }

    @Override
    public List<Bet> getBetByUserIdAndByMatchId(Long userId, Long matchId) {
        // TODO: getBetByUserIdAndByMatchId
        return null;
    }

    @Override
    public Bet getBetByIdAndByUserId(Long betId, Long userId) {
        User currentUser;
        Bet currentBet = null;

        if(userRepository.existsById(userId)) {
            currentUser = userRepository.findUserById(userId);
            // TODO : verfier le rôle de l'utilisateur
            // Si ROLE_USER -> poursuivre
            // Si ROLE_ADMIN ou ROLE_COMMENTATOR -> lancer une exception 'OperationNotAllowedException' et/ou 'BadRoleException'
            currentBet = betRepository.findBetByIdAndByUser(betId, currentUser);
        }
        return currentBet;
    }
}

package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Bet;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import fr.jpierrot.superbowlbackend.repository.BetRepository;
import fr.jpierrot.superbowlbackend.repository.UserRepository;
import fr.jpierrot.superbowlbackend.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    @Override
    public RegisterResponse createBetForUser(Bet newBet, Long userId) {
        User currentUser;
        List<Bet> existingBets = null;
        AtomicReference<Bet> existingBetForUser = new AtomicReference<>(new Bet());
        Bet betToUpdate = new Bet();
        String responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;

        if(userRepository.existsById(userId)) {
            currentUser = userRepository.findUserById(userId);

            if(currentUser.getIsEnabled() && currentUser.getIsPwdChecked()) {
                switch(currentUser.getRole()){
                    case ROLE_USER -> {
                        existingBets = betRepository.findBetsByMatch_Id(newBet.getMatch().getId());
                        if(!existingBets.isEmpty()) {
                            existingBets.forEach(bet -> {
                                try {
                                    existingBetForUser.set(betRepository.findBetByIdAndByUser(bet.getId(), currentUser));
                                } catch (Exception e) {
                                    System.out.println("Exception when trying to findBetByIdAndByUser(): " + e.getMessage());
                                }
                            });
                            // TODO : updateBetByUserId()
                            betToUpdate = existingBetForUser.get();
                            betToUpdate.setMatch(newBet.getMatch());
                            betToUpdate.setWager(newBet.getWager());
                            betToUpdate.setFinalOdds(newBet.getFinalOdds());
                            betRepository.save(betToUpdate);
                            responseBody = RegisterResponse.OK_201_UPDATED;
                        } else {
                            betRepository.save(newBet);
                            responseBody = RegisterResponse.OK_201_CREATED;
                        }
                    }
                    default -> responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;
                }
            }
        }

        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }

    @Override
    public RegisterResponse deleteBetByIdForUserId(Long userId, Long betId) {
        return null;
    }
}

package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.entities.Bet;
import fr.jpierrot.superbowlbackend.service.BetService;
import fr.jpierrot.superbowlbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_USER)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "GET")
public class UserBetWs {
    @Autowired
    private UserService userService;

    @Autowired
    private BetService betService;

    @GetMapping(path = "/{user_id}"+ ApiRegistration.API_BET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Bet> getAllBetsByUserId(@PathVariable("user_id") Long userId) {
        return betService.getAllBetByUserId(userId);
    }

    @GetMapping(path = "/{user_id}"+ ApiRegistration.API_BET + "/{bet_id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Bet getBetByIdAndByUserId(@PathVariable("bet_id") Long betId, @PathVariable("user_id") Long userId) {
        return betService.getBetByIdAndByUserId(betId, userId);
    }

    @PostMapping(path = "/{user_id}"+ ApiRegistration.API_BET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBody> createBetForUser(@PathVariable("user_id") Long userId) {
        // TODO: createBetForUser
        return null;
    }
}

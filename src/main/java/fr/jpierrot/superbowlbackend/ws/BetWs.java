package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Bet;
import fr.jpierrot.superbowlbackend.service.BetService;
import fr.jpierrot.superbowlbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_USER) /* default Route => /api/users/{user_id}/bets */
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "GET")
public class BetWs {
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
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> createBetForUser(@RequestBody Bet newBet, @PathVariable("user_id") Long userId) {
        RegisterResponse updateBetResponse = betService.createBetForUser(newBet, userId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ApiRegistration.API_REST+"/{user_id}"+ ApiRegistration.API_BET+"/{bet_id}")
                .buildAndExpand(userId, newBet.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", "createNewBet");

        return ResponseEntity.created(location).header(responseHeaders.toString()).body(updateBetResponse);
    }

    @DeleteMapping(path="/{user_id}" + ApiRegistration.API_BET + "/{bet_id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> deleteUserById(@PathVariable("user_id") Long userId,
                                                           @PathVariable("bet_id") Long betId) {
        return ResponseEntity.ok(betService.deleteBetByIdForUserId(userId, betId));
    }
}

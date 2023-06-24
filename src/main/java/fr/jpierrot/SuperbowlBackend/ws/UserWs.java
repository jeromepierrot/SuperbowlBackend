package fr.jpierrot.SuperbowlBackend.ws;

import fr.jpierrot.SuperbowlBackend.pojo.auth.RegisterResponse;
import fr.jpierrot.SuperbowlBackend.pojo.entities.User;
import fr.jpierrot.SuperbowlBackend.service.UserService;
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
    + ApiRegistration.API_USER)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "GET")
public class UserWs {
    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> createUser(@RequestBody User newUser){

        RegisterResponse createUserResponse = userService.createUser(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ApiRegistration.API_REST+ApiRegistration.API_USER+"/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", "createUser");

        return ResponseEntity.created(location).header(responseHeaders.toString()).body(createUserResponse);
    }
}

package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import fr.jpierrot.superbowlbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(ApiRegistration.API_REST
    + ApiRegistration.API_USER)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "GET")
public class UserWs {
    @Autowired
    private UserService userService;

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(path="/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> createUser(@RequestBody User newUser){

        RegisterResponse createUserResponse = userService.createUser(newUser);

        URI location = UriComponentsBuilder.fromPath(ApiRegistration.API_REST+ApiRegistration.API_USER)
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", "createUser");

        return ResponseEntity
                .created(location)
                .header(responseHeaders.toString())
                .body(createUserResponse);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateUserById(@RequestBody User userToUpdate, @PathVariable("id") Long id) {

        RegisterResponse updateUserResponse = userService.updateUserById(userToUpdate, id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build()
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", "updateUser");

        return ResponseEntity
                .created(location)
                .header(responseHeaders.toString())
                .body(updateUserResponse);
    }
}

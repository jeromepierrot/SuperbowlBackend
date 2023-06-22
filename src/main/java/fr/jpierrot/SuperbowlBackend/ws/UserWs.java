package fr.jpierrot.SuperbowlBackend.ws;

import fr.jpierrot.SuperbowlBackend.pojo.entities.User;
import fr.jpierrot.SuperbowlBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
    + ApiRegistration.API_USER)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "GET")
public class UserWs {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User newUser){
        // TODO: createUser
    }
}

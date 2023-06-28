package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.auth.DeleteResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Admin;
import fr.jpierrot.superbowlbackend.pojo.entities.Commentator;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import fr.jpierrot.superbowlbackend.service.AdminService;
import fr.jpierrot.superbowlbackend.service.CommentatorService;
import fr.jpierrot.superbowlbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_ADMIN
        + ApiRegistration.API_SADMIN) /* default Route => /api/admin27864/Administration...*/
@CrossOrigin(origins = "http://localhost:4200")
public class SuperAdminWs {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentatorService commentatorService;

    @Autowired
    private AdminService adminService;

    /* STANDARD-USERS MANAGEMENT */

    @GetMapping(path="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }


    @PutMapping(path="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateUserById(@RequestBody User userToUpdate, @PathVariable("id") Long id) {

        RegisterResponse updateUserResponse = userService.updateUserById(userToUpdate, id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ApiRegistration.API_REST+ApiRegistration.API_USER+"/{id}")
                .buildAndExpand(userToUpdate.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("ResponseHeader", "updateUser");

        return ResponseEntity.created(location).header(responseHeaders.toString()).body(updateUserResponse);
    }

    @DeleteMapping(path="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> deleteUserById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    /* COMMENTATOR-USERS MANAGEMENT */

    @GetMapping(path="/commentators", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commentator> getAllCommentators() {
        return commentatorService.getAllCommentators();
    }

    @GetMapping(path="/commentators/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Commentator getCommentatorById(@PathVariable Long id) {
        return commentatorService.getCommentatorById(id);
    }

    @PostMapping(path="/commentators/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RegisterResponse> createCommentator(@RequestBody Commentator newCommentator) {
        RegisterResponse createCommentatorResponse = commentatorService.createCommentator(newCommentator);

        if (createCommentatorResponse.getMessage().equals(RegisterResponse.OK_201_CREATED)) {
            URI location = UriComponentsBuilder.fromPath(ApiRegistration.API_REST+ApiRegistration.API_COM)
                    .path("/{id}")
                    .buildAndExpand(createCommentatorResponse.getId())
                    .toUri();
            HttpHeaders responseHeaders = new HttpHeaders();

            responseHeaders.setLocation(location);
            responseHeaders.set("ResponseHeader", "createCommentator success");

            return ResponseEntity.created(location)
                    .header(responseHeaders.toString())
                    .body(createCommentatorResponse);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(createCommentatorResponse);
        }


    }

    @PutMapping(path="/commentators/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateCommentator(@PathVariable("id") Long commentatorId) {
        // TODO : A implémenter
        return null;
    }

    @DeleteMapping(path="/commentators/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponse> deleteCommentatorById(@PathVariable("id") Long id) {
        // TODO : A implémenter
        return null;
    }

    /* ADMIN-USERS MANAGEMENT */

    @PostMapping(path = "/admin/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> createAdmin(@RequestBody Admin newAdmin) {
        RegisterResponse createAdminResponse = adminService.createAdmin(newAdmin);

        if (createAdminResponse.getMessage().equals(RegisterResponse.OK_201_CREATED)) {
            URI location = UriComponentsBuilder.fromPath(ApiRegistration.API_REST+ApiRegistration.API_ADMIN)
                    .path("/{id}")
                    .buildAndExpand(createAdminResponse.getId())
                    .toUri();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            responseHeaders.set("ResponseHeader", "createAdmin success");

            return ResponseEntity.created(location)
                    .header(responseHeaders.toString())
                    .body(createAdminResponse);
        } else {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(createAdminResponse);
        }
    }

    @PutMapping(path="/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> updateAdmin(@RequestBody Admin adminToUpdate, Long id) {
        RegisterResponse updateAdminResponse = adminService.updateAdminById(adminToUpdate, id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(ApiRegistration.API_REST+ApiRegistration.API_USER+"/{id}")
                .buildAndExpand(adminToUpdate.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        if(updateAdminResponse.getMessage().equals(RegisterResponse.OK_201_CREATED)) {
            responseHeaders.set("ResponseHeader", "updateAdmin success");

            return ResponseEntity.created(location)
                    .header(responseHeaders.toString())
                    .body(updateAdminResponse);
        } else {
            responseHeaders.set("ResponseHeader", "createAdmin failure");

            return ResponseEntity.status(HttpStatusCode.valueOf(403))
                    .header(responseHeaders.toString())
                    .body(updateAdminResponse);
        }
    }

    @DeleteMapping(path="/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> deleteAdminById(@PathVariable("id") Long id) {
        RegisterResponse deleteResponse = adminService.deleteAdminById(id);
        return ResponseEntity.accepted().body(deleteResponse);
    }
}
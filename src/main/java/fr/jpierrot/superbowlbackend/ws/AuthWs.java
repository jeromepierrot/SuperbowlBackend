package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.auth.AuthRequest;
import fr.jpierrot.superbowlbackend.pojo.auth.AuthResponse;
import fr.jpierrot.superbowlbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.API_AUTH)
public class AuthWs {

    @Autowired
    private AuthService authService;


    @PostMapping
    public ResponseEntity<AuthResponse> authenticate (
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

package fr.jpierrot.superbowlbackend.ws;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_ADMIN) /* default Route => /api/admin27864/... */
@CrossOrigin(origins = "http://localhost:4200")
public class AdminWs {

}

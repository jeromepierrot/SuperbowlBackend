package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.presentation.controllers.MvcRegistration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_ADMIN) // (default Route => /api/admin27864 should redirect to /admin27864)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"GET"})
public class AdminWs {

    @GetMapping
    public RedirectView redirectToAdminView() {
        return new RedirectView(MvcRegistration.ROOT);
    }
}

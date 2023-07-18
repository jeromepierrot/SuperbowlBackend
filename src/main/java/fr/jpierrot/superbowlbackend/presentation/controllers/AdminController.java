package fr.jpierrot.superbowlbackend.presentation.controllers;

import fr.jpierrot.superbowlbackend.pojo.entities.Commentator;
import fr.jpierrot.superbowlbackend.service.CommentatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MvcRegistration.ROOT) // (default Route => /admin27864 should redirect to /admin27864)
@CrossOrigin(origins = {"http://localhost:4200", "http://studi-superbowl.s3-website.eu-west-3.amazonaws.com"}, allowedHeaders = {"GET"})
public class AdminController {
    @Autowired
    private CommentatorService commentatorService;

    @GetMapping(path="/commentators/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Commentator getCommentatorById(@PathVariable Long id) {
        return commentatorService.getCommentatorById(id);
    }
}

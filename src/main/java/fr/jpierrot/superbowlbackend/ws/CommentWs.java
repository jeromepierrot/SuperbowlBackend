package fr.jpierrot.superbowlbackend.ws;

import fr.jpierrot.superbowlbackend.pojo.entities.Comment;
import fr.jpierrot.superbowlbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRegistration.API_REST
        + ApiRegistration.API_COMMENT)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"*"})
public class CommentWs {
    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment  getCommentById(@PathVariable("id") Long id) {
        return commentService.getCommentById(id);
    }


    @GetMapping(path = ApiRegistration.API_MATCH + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment>  getAllCommentsByMatchId(@PathVariable("id") Long id) {
        return commentService.getAllCommentsByMatchId(id);
    }
}

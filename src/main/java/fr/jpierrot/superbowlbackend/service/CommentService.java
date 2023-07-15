package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.entities.Comment;

import java.util.List;

public interface CommentService {
    /**
     * COMMENT SERVICES: Read *
     *      // For USERS
      */
    Comment getCommentById(Long id);

    List<Comment> getAllCommentsByMatchId(Long matchId);


    /**
     * COMMENT SERVICES: Post/Edit *
     *      // For COMMENTATORS
      */

}

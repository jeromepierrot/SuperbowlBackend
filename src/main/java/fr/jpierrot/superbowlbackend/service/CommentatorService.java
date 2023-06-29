package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Commentator;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;

import java.util.List;

public interface CommentatorService {
    /**
     *  COMMENTATOR SERVICES: Read *
     *      // For COMMENTATORS and USERS
     */

    Commentator getCommentatorById(Long id); /* for super admin usage only*/

    /**
     * COMMENTATOR SERVICES: ReadAll/Create/Update/Delete *
     *      // For SUPER ADMIN
      */

    List<Commentator> getAllCommentators(); /* for super admin usage only*/

    RegisterResponse createCommentator(Commentator newCommentator);

    RegisterResponse updateCommentatorById(Commentator commentator, Long id);

    RegisterResponse updateCommentatorByIdWithRole(Commentator commentator, Long id, Role role);

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteCommentatorById(Long id);

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteCommentatorByIdWithRole(Long id, Role role);
}

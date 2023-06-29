package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Commentator;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.repository.CommentatorRepository;
import fr.jpierrot.superbowlbackend.service.CommentatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentatorServiceImpl implements CommentatorService {
    @Autowired
    private CommentatorRepository commentatorRepository;

    @Override
    public Commentator getCommentatorById(Long id) {
        return commentatorRepository.findByIdAndRoleIs(id, Role.ROLE_COMMENTATOR);
    }

    @Override
    public List<Commentator> getAllCommentators() {
        List<Commentator> commentatorsList = commentatorRepository.findAllByRoleIs(Role.ROLE_COMMENTATOR);

        if(commentatorsList.isEmpty()) {
            return commentatorRepository.findAllByRoleIs(Role.ROLE_COMMENTATOR);
        }
        return commentatorsList;
    }

    @Override
    public RegisterResponse createCommentator(Commentator newCommentator) {
        String responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;
        Commentator commentator;

        if(commentatorRepository.findByEmailIs(newCommentator.getEmail()) != null) {
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            return RegisterResponse.builder()
                    .message(responseBody)
                    .build();
        }

        // TODO : Encrypt the password when security is up
        commentator = Commentator.builder()
                .firstname(newCommentator.getFirstname())
                .lastname(newCommentator.getLastname())
                .email(newCommentator.getEmail())
                .password(newCommentator.getPassword())
                .isEnabled(true)
                .isPwdChecked(false)
                .role(Role.ROLE_COMMENTATOR)
                .build();

        /* insert into database */
        commentator = commentatorRepository.save(commentator); /* we get newly DBMS inserted infos back here */
        responseBody = RegisterResponse.OK_201_CREATED;

        return RegisterResponse.builder()
                .message(responseBody)
                .id(commentator.getId()) /* id returned by DBMS */
                .build();
    }

    @Override
    public RegisterResponse updateCommentatorById(Commentator commentator, Long id) {

        return updateCommentatorByIdWithRole(commentator, id, Role.ROLE_COMMENTATOR);
    }

    @Override
    public RegisterResponse updateCommentatorByIdWithRole(Commentator commentator, Long id, Role role) {
        Commentator commentatorToUpdate;
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND; /* Default error message*/

        if(commentatorRepository.existsById(id)){
            commentatorToUpdate = commentatorRepository.findByIdAndRoleIs(id, role);
            if (commentatorToUpdate != null) {
                if(commentatorToUpdate.isActivated() && commentatorToUpdate.hasRole(role)) {
                    // TODO : Encrypt the password when security is up
                    commentatorToUpdate.setLastname(commentator.getLastname());
                    commentatorToUpdate.setFirstname(commentator.getFirstname());
                    commentatorToUpdate.setEmail(commentator.getEmail());
                    commentatorToUpdate.setPassword(commentator.getPassword());
                    try {
                        commentatorRepository.save(commentatorToUpdate);
                        responseBody = RegisterResponse.OK_201_UPDATED;
                    } catch (Exception e){
                        responseBody = e.getMessage();
                    }
                } else if (!commentatorToUpdate.isEnabled() || !commentatorToUpdate.hasRole(role)) {
                    responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
                } else if (!commentatorToUpdate.isPwdChecked()) {
                    responseBody = ErrorResponse.ERROR_401_ACCOUNT_NOT_CHECKED;
                }
            }
        }

        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }

    @Override
    public RegisterResponse deleteCommentatorById(Long id) {
        return deleteCommentatorByIdWithRole(id, Role.ROLE_COMMENTATOR); /* we force the role ROLE_COMMENTATOR here */
    }

    @Override
    public RegisterResponse deleteCommentatorByIdWithRole(Long id, Role role) {
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;

        Commentator commentatorToDelete = commentatorRepository.findByIdAndRoleIs(id, role);
        if(commentatorToDelete != null) {
            if(commentatorToDelete.hasRole(role)) {// Check ROLE before deleting, must be ROLE_COMMENTATOR
                try {
                    commentatorRepository.deleteById(id);
                    responseBody = RegisterResponse.OK_201_DELETED;
                } catch (Exception e) {
                    responseBody = e.getMessage();
                }
            } else {
                responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            }
        }
        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }
}

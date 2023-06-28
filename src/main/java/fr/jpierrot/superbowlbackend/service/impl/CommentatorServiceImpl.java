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
            // TODO: implementation
            return null;
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

        // TODO: Encrypt the password when security is up
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
        Commentator commentatorToUpdate;
        if(commentatorRepository.existsById(id)) {
            commentatorToUpdate = commentatorRepository.findById(id).orElse(null);
        }
        // TODO : Encrypt the password when security is up
        commentatorToUpdate = Commentator.builder()
                .firstname(commentator.getFirstname())
                .lastname(commentator.getLastname())
                .email(commentator.getEmail())
                .password(commentator.getPassword())
                .role(Role.ROLE_COMMENTATOR)
                .build();

        commentatorRepository.save(commentatorToUpdate);

        String responseBody = RegisterResponse.OK_201_CREATED;

        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }
}

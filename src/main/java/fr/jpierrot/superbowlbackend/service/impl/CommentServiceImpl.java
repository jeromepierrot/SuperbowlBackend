package fr.jpierrot.superbowlbackend.service.impl;


import fr.jpierrot.superbowlbackend.pojo.entities.Comment;
import fr.jpierrot.superbowlbackend.repository.CommentRepository;
import fr.jpierrot.superbowlbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findCommentById(id);
    }

    @Override
    public List<Comment> getAllCommentsByMatchId(Long matchId) {
        return commentRepository.findCommentsByMatchId(matchId);
    }
}

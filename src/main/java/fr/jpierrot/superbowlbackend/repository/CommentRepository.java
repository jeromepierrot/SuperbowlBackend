package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findCommentById(Long id);

    @Query("SELECT c FROM Comment c WHERE c.match.id = ?1")
    List<Comment> findCommentsByMatchId(Long matchId);
}

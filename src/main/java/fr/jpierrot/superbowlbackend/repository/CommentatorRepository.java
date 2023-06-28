package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Commentator;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentatorRepository extends CrudRepository<Commentator, Long> {
    List<Commentator> findAllByRoleIs(Role role);

    Commentator findByIdAndRoleIs(Long id, Role role);

    Commentator findByEmailIs(String email);
}

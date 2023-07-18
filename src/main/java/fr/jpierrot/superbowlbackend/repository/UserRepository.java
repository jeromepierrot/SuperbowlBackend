package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findAllByRoleIs(Role role);

    @Query("SELECT u FROM User u WHERE u.id = ?1 ")
    User findUserById(Long id);

    Optional<User> findByEmail(String email);

    User findByIdAndRoleIs(Long id, Role role);

    User findByEmailIs(String email);
}

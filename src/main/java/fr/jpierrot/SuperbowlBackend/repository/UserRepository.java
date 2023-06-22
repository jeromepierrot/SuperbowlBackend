package fr.jpierrot.SuperbowlBackend.repository;

import fr.jpierrot.SuperbowlBackend.pojo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u")
    List<User> findAllUsers();

    @Query("SELECT u FROM User u WHERE u.id = ?1 ")
    User findUserById(Long id);
}

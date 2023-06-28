package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Admin;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    List<Admin> findAllByRoleIs(Role role);

    @Query("SELECT u FROM User u WHERE u.id = ?1 ")
    User findAdminById(Long id);

    Admin findByIdAndRoleIs(Long id, Role role);

    Admin findByEmailIs(String email);
}
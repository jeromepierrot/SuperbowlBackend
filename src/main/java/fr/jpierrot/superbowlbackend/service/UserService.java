package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    // USERS SERVICES: Read, Create, Update //
    // For USER with ROLE_USER

    User getUserById(Long id);

    User getUserByEmail(String email) throws UsernameNotFoundException;

    RegisterResponse createUser(RegisterRequest newUser);

    RegisterResponse createUserWithRole(RegisterRequest newUser, Role role);

    RegisterResponse updateUserById(User user, Long id);

    RegisterResponse updateUserByIdWithRole(User user, Long id, Role role);

    // USERS as ROLE_USER SERVICES: Read, Delete //
    List<User> getAllUsers();

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteUserById(Long id);

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteUserByIdWithRole(Long id, Role role);
}

package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.pojo.entities.User;

import java.util.List;

public interface UserService {
    // USERS SERVICES: Read, Create, Update //
    // For USER with ROLE_USER

    User getUserById(Long id);

    RegisterResponse createUser(User newUser);

    RegisterResponse createUserWithRole(User newUser, Role role);

    RegisterResponse updateUserById(User user, Long id);

    RegisterResponse updateUserByIdWithRole(User user, Long id, Role role);

    // USERS as ROLE_USER SERVICES: Read, Delete //
    List<User> getAllUsers();

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteUserById(Long id);

    // TODO : change returned type to DeleteResponse (not hurry)
    RegisterResponse deleteUserByIdWithRole(Long id, Role role);
}

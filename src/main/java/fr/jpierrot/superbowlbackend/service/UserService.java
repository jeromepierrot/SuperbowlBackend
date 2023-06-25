package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    RegisterResponse createUser(User newUser);

    RegisterResponse updateUserById(User user, Long id);

    RegisterResponse deleteUserById(Long id);

}

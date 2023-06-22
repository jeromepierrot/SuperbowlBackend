package fr.jpierrot.SuperbowlBackend.service;

import fr.jpierrot.SuperbowlBackend.pojo.entities.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    User getUserById(Long id);
}

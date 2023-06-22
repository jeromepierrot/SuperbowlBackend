package fr.jpierrot.SuperbowlBackend.service.impl;

import fr.jpierrot.SuperbowlBackend.pojo.entities.User;
import fr.jpierrot.SuperbowlBackend.repository.UserRepository;
import fr.jpierrot.SuperbowlBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }
}

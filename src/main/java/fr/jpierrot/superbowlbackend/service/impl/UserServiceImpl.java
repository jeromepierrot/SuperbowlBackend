package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import fr.jpierrot.superbowlbackend.repository.UserRepository;
import fr.jpierrot.superbowlbackend.service.UserService;
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

    @Override
    public RegisterResponse createUser(User newUser) {

        /* insert into database */
        userRepository.save(newUser);

        String responseBody = RegisterResponse.OK_201_CREATED;

        return RegisterResponse.builder()
                .body(responseBody)
                .build();
    }

    @Override
    public RegisterResponse updateUserById(User user, Long id) {
        User userToUpdate;
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
        if(userRepository.existsById(id)){
            userToUpdate = userRepository.findUserById(id);
            if(userToUpdate.getIsEnabled() && userToUpdate.getIsPwdChecked()) {
                // only name and firstname can be modified with this method
                // email = login and password need special method to be updated
                userToUpdate.setName(user.getName());
                userToUpdate.setFirstname(user.getFirstname());
                userRepository.save(userToUpdate);
                responseBody = RegisterResponse.OK_201_UPDATED;
            } else if (userToUpdate.getIsEnabled()) {
                responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            } else if (userToUpdate.getIsPwdChecked()) {
                responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;
            }
        }

        return RegisterResponse.builder()
                .body(responseBody)
                .build();
    }

    @Override
    public RegisterResponse deleteUserById(Long id) {
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            responseBody = RegisterResponse.OK_201_DELETED;
        }
        return RegisterResponse.builder()
                .body(responseBody)
                .build();
    }
}

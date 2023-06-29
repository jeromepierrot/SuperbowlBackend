package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
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
        return userRepository.findAllByRoleIs(Role.ROLE_USER);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findByIdAndRoleIs(id, Role.ROLE_USER);
    }

    @Override
    public RegisterResponse createUser(User newUser) {
        return createUserWithRole(newUser, Role.ROLE_USER);
    }

    @Override
    public RegisterResponse createUserWithRole(User newUser, Role role) {
        String responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;
        User userToCreate = null;

        if( userRepository.findByEmailIs(newUser.getEmail()) != null ) {
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            return RegisterResponse.builder()
                    .message(responseBody)
                    .build();
        }

        if ( !newUser.hasRequiredFields()) {
            responseBody = ErrorResponse.ERROR_400_BAD_REQUEST;
            return RegisterResponse.builder()
                    .message(responseBody)
                    .build();
        }

        // TODO : Encrypt the password when security is up
        userToCreate = User.builder()
                .email(newUser.getEmail())
                .lastname(newUser.getLastname())
                .firstname(newUser.getFirstname())
                .password(newUser.getPassword())
                .isEnabled(true)
                .isPwdChecked(false)
                .role(role)
                .build();

        /* insert into database */
        try {
            userToCreate = userRepository.save(userToCreate); /* we get newly DBMS inserted infos back here */
            responseBody = RegisterResponse.OK_201_CREATED;
        } catch (Exception e) {
            responseBody = e.getMessage();
        }

        return RegisterResponse.builder()
                .message(responseBody)
                .id(userToCreate.getId()) /* id returned by DBMS */
                .build();
    }

    @Override
    public RegisterResponse updateUserById(User user, Long id) {
        return updateUserByIdWithRole(user, id, Role.ROLE_USER);
    }

    @Override
    public RegisterResponse updateUserByIdWithRole(User user, Long id, Role role) {
        User userToUpdate;
        String responseBody = ErrorResponse.ERROR_404_NOT_FOUND; /* Default error message*/

        // TODO : need to solve the many to many association and cascade update/delete

        if(userRepository.existsById(id)){
            userToUpdate = userRepository.findUserById(id);
            if(userToUpdate != null) {
                if (userToUpdate.isActivated() && userToUpdate.hasRole(role)) {
                    // only name and firstname can be modified with this method
                    // email = login and password need special method to be updated
                    userToUpdate.setLastname(user.getLastname());
                    userToUpdate.setFirstname(user.getFirstname());
                    try {
                        userRepository.save(userToUpdate);
                        responseBody = RegisterResponse.OK_201_UPDATED;
                    } catch (Exception e) {
                        responseBody = e.getMessage();
                    }
                } else if (!userToUpdate.isEnabled() || !userToUpdate.hasRole(role)) {
                    responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
                } else if (!userToUpdate.isPwdChecked()) {
                    responseBody = ErrorResponse.ERROR_401_ACCOUNT_NOT_CHECKED;
                } else {
                    responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
                }
            }
        }

        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }

    @Override
    public RegisterResponse deleteUserById(Long id) {
        return this.deleteUserByIdWithRole(id, Role.ROLE_USER);
    }

    /**
     * only standard users can be deleted by this method
     */
    @Override
    public RegisterResponse deleteUserByIdWithRole(Long id, Role role) {
        String responseBody;

        User userToDelete = userRepository.findByIdAndRoleIs(id, role);

        // TODO : need to solve the many to many association and cascade update/delete

        if(userToDelete != null) {
            if (userToDelete.hasRole(role)) {            // Check ROLE before deleting, must be ROLE_USER
                try {
                    userRepository.deleteById(id);
                    responseBody = RegisterResponse.OK_201_DELETED;
                } catch (Exception e) {
                    responseBody = e.getMessage();
                }
            } else {
                responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            }
        } else {
            responseBody = ErrorResponse.ERROR_404_NOT_FOUND;
        }
        return RegisterResponse.builder()
                .message(responseBody)
                .build();
    }
}

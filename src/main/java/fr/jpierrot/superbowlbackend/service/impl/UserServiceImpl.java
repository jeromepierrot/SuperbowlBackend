package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.auth.ErrorResponse;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterRequest;
import fr.jpierrot.superbowlbackend.pojo.auth.RegisterResponse;
import fr.jpierrot.superbowlbackend.pojo.entities.Role;
import fr.jpierrot.superbowlbackend.pojo.entities.User;
import fr.jpierrot.superbowlbackend.repository.UserRepository;
import fr.jpierrot.superbowlbackend.service.UserService;
import fr.jpierrot.superbowlbackend.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByRoleIs(Role.ROLE_USER);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findByIdAndRoleIs(id, Role.ROLE_USER);
    }

    @Override
    public User getUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public RegisterResponse createUser(RegisterRequest newUser) {
        return createUserWithRole(newUser, Role.ROLE_USER);
    }

    @Override
    public RegisterResponse createUserWithRole(RegisterRequest newUser, Role role) {
        String responseBody = ErrorResponse.ERROR_401_UNAUTHORIZED;
        User user = null;

        /* Check at required fields */
        if ( !newUser.hasRequiredFields()) {
            responseBody = ErrorResponse.ERROR_400_BAD_REQUEST;
            return RegisterResponse.builder()
                    .message(responseBody)
                    .token("")
                    .build();
        }

        /* Check if email already exists in the DB */
        if( userRepository.findByEmailIs(newUser.getEmail()) != null ) {
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
            return RegisterResponse.builder()
                    .message(responseBody)
                    .token("")
                    .build();
        }

        user = User.builder()
                .email(newUser.getEmail())
                .lastname(newUser.getLastname())
                .firstname(newUser.getFirstname())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .isEnabled(true)
                .isPwdChecked(false)
                .role(role)
                .build();

        /* insert into database */
        try {
            user = userRepository.save(user); /* we get newly DBMS inserted infos back here */
            responseBody = RegisterResponse.OK_201_CREATED;
        } catch (Exception e) {
            responseBody = ErrorResponse.ERROR_403_FORBIDDEN;
        }

        /* generate JWT with extra claims */
        Map<String, Object> extraClaims  = new HashMap<>();
        extraClaims.put("id", user.getId());
        var jwtToken = jwtService.generateToken(extraClaims, user);

/*        *//* generate JWT WITHOUT extra claims *//*
        var jwtToken = jwtService.generateToken(user);*/

        return RegisterResponse.builder()
                .message(responseBody)
                .token(jwtToken)
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

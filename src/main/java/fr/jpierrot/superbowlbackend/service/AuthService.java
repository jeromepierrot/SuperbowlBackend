package fr.jpierrot.superbowlbackend.service;

import fr.jpierrot.superbowlbackend.pojo.auth.*;

public interface AuthService {

    /**
     * @param request to authenticate any 'User', 'Commmentator' or 'Admin'
     * @return one valid JWT token according to JwtAuthFilter configuration
     */
    public AuthResponse authenticate(AuthRequest request);
}

package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Stores any authentication request from REST API
 * this request will check if the user already exists into the database
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email; // == username in UserDetails inherited class
    private String password;
}
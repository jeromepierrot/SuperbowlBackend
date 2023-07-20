package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.*;

/**
 * Stores Authentication response to send to the REST API back.
 * It will send a JWT (token) back to the Front-end if authentification is a success.
 * Otherwise, Http ErrorCode 401 is returned.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;

}

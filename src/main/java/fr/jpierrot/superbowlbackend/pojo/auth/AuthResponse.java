package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.*;

/**
 * Stores Authentication response to send to the REST API back.
 * TODO: complete the Javadoc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String body;

}

package fr.jpierrot.SuperbowlBackend.pojo.auth;

import lombok.*;

import java.util.Map;

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

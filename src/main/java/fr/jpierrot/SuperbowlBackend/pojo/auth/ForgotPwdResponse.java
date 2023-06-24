package fr.jpierrot.SuperbowlBackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Stores Forgot Password response to send to the REST API back.
 * TODO: complete the Javadoc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPwdResponse {

    private String body;
}

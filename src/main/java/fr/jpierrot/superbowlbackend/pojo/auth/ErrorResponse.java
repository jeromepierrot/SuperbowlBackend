package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    public static final String ERROR_400_BAD_REQUEST = "Bad request";
    public static final String ERROR_401_UNAUTHORIZED = "Resource access denied";
    public static final String ERROR_403_FORBIDDEN = "Resource access denied";
    public static final String ERROR_404_NOT_FOUND = "Resource not found";
    public static final String ERROR_405_NOT_ALLOWED = "Method not allowed for this resource";
    public static final String ERROR_408_TIMEOUT = "Timeout";
}

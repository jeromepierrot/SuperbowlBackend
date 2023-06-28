package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    public static final String ERROR_400_BAD_REQUEST = "Bad request";
    public static final String ERROR_401_UNAUTHORIZED = "Unauthorized access";
    public static final String ERROR_401_ACCOUNT_NOT_CHECKED = "Current account not validated";
    public static final String ERROR_403_FORBIDDEN = "Access denied";
    public static final String ERROR_404_NOT_FOUND = "Resource not found";
    public static final String ERROR_405_NOT_ALLOWED = "Method not allowed for this resource";
    public static final String ERROR_408_TIMEOUT = "Timeout";

    private String error_message;
}

package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetResponse {
    public static final String HttpCode_OK_200 = "200";
    public static final String HttpCode_Redirection_301 = "301";
    public static final String HttpCode_BadRequest_400 = "400";
    public static final String HttpCode_Unauthorized_401 = "401";
    public static final String HttpCode_Forbidden_403 = "403";
    public static final String HttpCode_NotFound_404 = "404";
    public static final String HttpCode_Timeout_408 = "408";

    private String message;
}

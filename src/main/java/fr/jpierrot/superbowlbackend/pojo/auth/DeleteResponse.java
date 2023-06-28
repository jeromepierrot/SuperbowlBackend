package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {
    public static final String OK_200_CREATED = "Data deleted with success";
    private String body;
}

package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    public static final String OK_201_CREATED = "Data is created successfully";
    public static final String OK_201_UPDATED = "Data is updated successfully";
    public static final String OK_201_DELETED = "Data is deleted successfully";

    private String body;

}
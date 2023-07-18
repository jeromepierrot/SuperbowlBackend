package fr.jpierrot.superbowlbackend.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRegisterResponse {
    public static final String OK_201_CREATED = "Data created successfully";
    public static final String OK_201_UPDATED = "Data updated successfully";
    public static final String OK_201_DELETED = "Data has been deleted successfully";

    private String message;

}
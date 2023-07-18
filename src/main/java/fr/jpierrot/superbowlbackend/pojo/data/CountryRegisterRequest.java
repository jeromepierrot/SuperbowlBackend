package fr.jpierrot.superbowlbackend.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CountryRegisterRequest {
    private String name;

    public boolean hasRequiredFields() {
        return this.getName() != null && !this.getName().isEmpty();
    }
}

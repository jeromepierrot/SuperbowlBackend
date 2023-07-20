package fr.jpierrot.superbowlbackend.pojo.data;

import fr.jpierrot.superbowlbackend.pojo.entities.Country;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRegisterRequest {
    private String name ;
    private Country country; // can be null

    public boolean hasRequiredFields() {
        return this.getName() != null && !this.getName().isEmpty();
    }
}

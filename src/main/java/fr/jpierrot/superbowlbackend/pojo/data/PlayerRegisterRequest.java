package fr.jpierrot.superbowlbackend.pojo.data;

import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRegisterRequest {
    private String lastname;
    private String firstname ;
    private Integer number;
    private Team team; // can be null

    public boolean hasRequiredFields() {
        return this.getLastname() != null && !this.getLastname().isEmpty()
                && this.getFirstname() != null && !this.getFirstname().isEmpty()
                && this.getNumber() != null
                && this.getNumber() > 0 && this.getNumber() < 100;
    }
}

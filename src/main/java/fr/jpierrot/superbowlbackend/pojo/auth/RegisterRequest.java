package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email; /* email = login (= username for UserDetails interface */
    private String lastname;
    private String firstname;
    private String password; /* password must have 12 char, incl. 1 MAJ, 1 min, 1 number, 1 special char */

    /**
     * Check if User instance has fullfilled all required fields
     */
    public boolean hasRequiredFields() {
        return this.getEmail() != null && !this.getEmail().isEmpty()
                && this.getLastname() != null && !this.getLastname().isEmpty()
                && this.getFirstname() != null && !this.getFirstname().isEmpty()
                && this.getPassword() != null && !this.getPassword().isEmpty();
    }
}
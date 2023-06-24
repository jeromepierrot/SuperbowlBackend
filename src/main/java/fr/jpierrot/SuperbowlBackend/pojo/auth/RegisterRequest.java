package fr.jpierrot.SuperbowlBackend.pojo.auth;

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
    private String name;
    private String firstname;
    private String password; /* password must have 12 char, incl. 1 MAJ, 1 min, 1 number, 1 special char */
}
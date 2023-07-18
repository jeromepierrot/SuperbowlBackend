package fr.jpierrot.superbowlbackend.pojo.auth;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@RequiredArgsConstructor
public class AdminRegisterRequest extends RegisterRequest {
    private Boolean isSuperAdmin;

    /**
     * Check if User instance has fullfilled all required fields
     */
    @Override
    public boolean hasRequiredFields() {
        return this.getEmail() != null && !this.getEmail().isEmpty()
                && this.getLastname() != null && !this.getLastname().isEmpty()
                && this.getFirstname() != null && !this.getFirstname().isEmpty()
                && this.getPassword() != null && !this.getPassword().isEmpty()
                && this.getIsSuperAdmin() != null;
    }
}

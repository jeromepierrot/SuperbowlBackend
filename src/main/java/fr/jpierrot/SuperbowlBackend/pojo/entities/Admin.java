package fr.jpierrot.SuperbowlBackend.pojo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@RequiredArgsConstructor
@Entity(name = "admins")
@DiscriminatorValue("A")
public class Admin extends User {
    @Column(name = "is_super_admin")
    private Boolean isSuperAdmin;
}

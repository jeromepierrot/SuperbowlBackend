package fr.jpierrot.superbowlbackend.pojo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "admins")
@DiscriminatorValue("A")
public class Admin extends User {
    @Builder.Default
    @Column(name = "is_super_admin")
    private Boolean isSuperAdmin = false;
}

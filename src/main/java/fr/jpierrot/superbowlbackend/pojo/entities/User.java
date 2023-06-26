package fr.jpierrot.superbowlbackend.pojo.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_users_email", columnNames = "email")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@DiscriminatorValue("U")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /* Careful: email here is the login, so it is equivalent to 'username' in UserDetails interface.
    'getUsername()' is its Getter/Setter */
    @Column(name="email", nullable = false)
    private String email;

    /* Careful: if this attribute is called 'password', getPassword() won't appear but needs to be overridden from UserDetails */
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="firstname", nullable = false)
    private String firstname;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Builder.Default
    @Column(name="is_enabled", nullable = false)
    private Boolean isEnabled = false;

    @Builder.Default
    @Column(name="is_pwd_checked", nullable = false)
    private Boolean isPwdChecked = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "creation_date")
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "modification_date")
    private ZonedDateTime modifiedDate = ZonedDateTime.now();
}

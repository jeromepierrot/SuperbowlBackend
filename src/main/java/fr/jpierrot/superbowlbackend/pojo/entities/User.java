package fr.jpierrot.superbowlbackend.pojo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_users_email", columnNames = "email")
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = User.class)
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

    @Column(name="lastname", nullable = false)
    private String lastname;

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

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_bets",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "bet_id") })
    @JsonManagedReference
    private List<Bet> bets = null;

    /**
     * Check if User instance has fullfilled all required fields
     */
    public boolean hasRequiredFields() {
        return this.getEmail() != null && !this.getEmail().isEmpty()
                && this.getLastname() != null && !this.getLastname().isEmpty()
                && this.getFirstname() != null && !this.getFirstname().isEmpty()
                && this.getPassword() != null && !this.getPassword().isEmpty();
    }

    /**
     * Check if User instance has an activated account,
     * meaning both isEnabled and isPwsChecked are true
     */
    public boolean isActivated() {
        return this.getIsEnabled() && this.getIsPwdChecked();
    }

    /**
     * Check if User instance has an enabled account,
     * disabled mean the user is either banished or has an issue
     */
    public boolean isEnabled() {
        return this.getIsEnabled();
    }

    /**
     * Check if User's account is verified,
     * disabled mean the user didn't change his auto-generated password yet
     * (= temporary password active)
     */
    public boolean isPwdChecked() {
        return this.getIsPwdChecked();
    }

    public boolean hasRole(Role role) { return this.role == role; }

    /**
     * Attach a bet to this user, and the User to this bet ONLY IF this bet is not already attached to another user
     * @return either true if newBet added, or false in any other case
     */
    public boolean addBet(Bet newBet) {
        if(this.bets == null) {
            this.bets = new ArrayList<>();
        }
        if(newBet != null && newBet.getUsers() == null) {
            this.bets.add(newBet);
            newBet.addUser(this);
            return true;
        }
        return false;
    }
}

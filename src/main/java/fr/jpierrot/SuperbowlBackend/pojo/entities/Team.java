package fr.jpierrot.SuperbowlBackend.pojo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "teams",
        uniqueConstraints = {
            @UniqueConstraint(name = "UK_teams_name", columnNames = "name")
})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;
}

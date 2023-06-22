package fr.jpierrot.SuperbowlBackend.pojo.entities;

import jakarta.persistence.*;
import lombok.*;



@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "countries",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_countries_name", columnNames = "name")
        })
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;
}

package fr.jpierrot.SuperbowlBackend.pojo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;


@SuperBuilder
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

    @Builder.Default
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country = null;

/*    @OneToMany(mappedBy = "team")
    private Set<Player> players;*/

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "creation_date")
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    @Column(name = "modification_date")
    private ZonedDateTime modifiedDate = ZonedDateTime.now();

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}

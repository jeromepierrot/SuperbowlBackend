package fr.jpierrot.SuperbowlBackend.pojo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

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
        return "Player{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", number=" + number +
                ", team=" + team +
                '}';
    }
}

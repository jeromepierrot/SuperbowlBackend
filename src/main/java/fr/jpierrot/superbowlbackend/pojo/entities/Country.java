package fr.jpierrot.superbowlbackend.pojo.entities;

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

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

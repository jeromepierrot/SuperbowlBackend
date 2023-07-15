package fr.jpierrot.superbowlbackend.pojo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "commentators")
@DiscriminatorValue("C")
public class Commentator extends User {
    @Builder.Default
    @JsonBackReference
    @OneToMany(mappedBy = "commentator")
    private Set<Comment> comments = null;
}

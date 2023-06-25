package fr.jpierrot.superbowlbackend.pojo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@RequiredArgsConstructor
@Entity(name = "commentators")
@DiscriminatorValue("C")
public class Commentator extends User {
    @OneToMany(mappedBy = "commentator")
    private Set<Comment> comments;
}

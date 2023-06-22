package fr.jpierrot.SuperbowlBackend.pojo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "commentator_user_id")
    private Commentator commentator;

    private String postContent;

    private ZonedDateTime postDate;

    private ZonedDateTime editDate;

}

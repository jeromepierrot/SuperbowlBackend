package fr.jpierrot.superbowlbackend.pojo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Comment.class)
public class Comment {
    private static Log log = LogFactory.getLog(Comment.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "match_id", foreignKey = @ForeignKey(name = "FK_comments_match"))
    private Match match;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "commentator_user_id", foreignKey = @ForeignKey(name = "FK_comments_commentator"))
    private Commentator commentator;

    private String postContent;

    private ZonedDateTime postDate;

    private ZonedDateTime editDate;

    public void logNewCommentUserAttempt() {
        log.info("Attempting to add new comment for the match: " + match.toString()
        + ", author: " + commentator.toString());
    }
}

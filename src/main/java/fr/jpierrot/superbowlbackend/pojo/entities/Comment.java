package fr.jpierrot.superbowlbackend.pojo.entities;

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
public class Comment {
    private static Log log = LogFactory.getLog(Comment.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", foreignKey = @ForeignKey(name = "FK_comments_match"))
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentator_user_id", foreignKey = @ForeignKey(name = "FK_comments_commentator"))
    private Commentator commentator;

    private String postContent;

    private ZonedDateTime postDate;

    private ZonedDateTime editDate;

    public void logNewCommentUserAttempt() {
        log.info("Attempting to add new comment for the match: " + match.toString()
        + ", author: " + commentator.toString());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", match=" + match +
                ", commentator=" + commentator +
                ", postContent='" + postContent + '\'' +
                ", postDate=" + postDate +
                ", editDate=" + editDate +
                '}';
    }
}

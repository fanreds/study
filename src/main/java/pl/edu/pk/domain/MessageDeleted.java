package pl.edu.pk.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 5/15/13
 * Time: 1:45 PM
 */
@Entity
@Table(name = "MESSAGE_DELETED")
public class MessageDeleted implements Serializable {
    @Id
    @SequenceGenerator(name = "MESSAGE_DELETED_ID_SEQUENCE", sequenceName = "MESSAGE_DELETED_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_DELETED_ID_SEQUENCE")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "MESSAGE_ID")
    private Message message;

    public MessageDeleted() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

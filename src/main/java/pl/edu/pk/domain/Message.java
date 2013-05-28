package pl.edu.pk.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/6/13
 * Time: 9:04 PM
 */
@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {

    @Id
    @SequenceGenerator(name = "MESSAGE_ID_SEQUENCE", sequenceName = "MESSAGE_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQUENCE")
    private Long id;
    @Column(name = "SUBJECT")
    private String subject;
    @ManyToOne(optional = true)
    @JoinColumn(name = "RECIPIENT_ID")
    @ForeignKey(name = "FK___MESSAGE___USER_RECIPIENT")
    @SuppressWarnings("JpaAttributeTypeInspection")
    private User recipient;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    @ForeignKey(name = "FK___MESSAGE___USER_SENDER")
    @SuppressWarnings("JpaAttributeTypeInspection")
    private User sender;
    @Column(name = "CONTENT")
    private byte[] content;
    @OneToOne(optional = true)
    @JoinColumn(name = "GROUP_ID", nullable = true)
    private Group group;
    @OneToOne(optional = true)
    @JoinColumn(name = "SPECIALIZATION_ID", nullable = true)
    private Specialization specialization;
    @Column(name = "FOR_ALL", nullable = false)
    private boolean forAll;
    @Column(name = "READ", nullable = false)
    private boolean read;
    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(nullable = true, name = "SENTDATE")
    private Date sentDate;
    transient private String contentString;

    public Message() {
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public boolean isForAll() {
        return forAll;
    }

    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

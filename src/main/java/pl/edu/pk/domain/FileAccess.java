package pl.edu.pk.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/15/13
 * Time: 9:39 PM
 */
@Entity
@Table(name = "FILE_ACCESS")
public class FileAccess implements Serializable {

    @Id
    @SequenceGenerator(name = "FILE_ACCESS_ID_SEQUENCE", sequenceName = "FILE_ACCESS_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ACCESS_ID_SEQUENCE")
    private Long id;
    @OneToOne(optional = true)
    @JoinColumn(name = "GROUP_ID",nullable = true)
    private Group group;
    @OneToOne(optional = true)
    @JoinColumn(name = "SPECIALIZATION_ID",nullable = true)
    private Specialization specialization;
    @OneToMany
    @Column(name = "USER_ID",nullable = true)
    private List<User> users;

    public FileAccess() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<User>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

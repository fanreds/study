package pl.edu.pk.domain;

import org.hibernate.annotations.ForeignKey;

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
    @Column(name = "ID")
    private Long id;
//    @OneToOne(optional = true)
//    @JoinColumn(name = "GROUP_ID", nullable = true)
//    private Group group;
//    @OneToOne(optional = true)
//    @JoinColumn(name = "SPECIALIZATION_ID", nullable = true)
//    private Specialization specialization;
//    @ManyToMany
//    @JoinTable(
//            name = "FILE_ACCESS__USER",
//            joinColumns = {@JoinColumn(name = "FILE_ACCESS_ID", referencedColumnName = "ID")},
//            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
//    @ForeignKey(name = "FK___FILE_ACCESS___FILE_ACCESS___USER", inverseName = "FK___FILE_ACCESS___USER___FILE_ACCESS")
//    private List<User> users;
    @Column(name = "SHARE_ALL", nullable = false)
    private boolean shareAll;

    public FileAccess() {
    }

    public boolean getShareAll() {
        return shareAll;
    }

    public void setShareAll(boolean all) {
        this.shareAll = all;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Group getGroup() {
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }
//
//    public Specialization getSpecialization() {
//        return specialization;
//    }
//
//    public void setSpecialization(Specialization specialization) {
//        this.specialization = specialization;
//    }

//    public List<User> getUsers() {
//        if (users == null) {
//            users = new ArrayList<User>();
//        }
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}

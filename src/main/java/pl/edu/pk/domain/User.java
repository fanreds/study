package pl.edu.pk.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/10/13
 * Time: 12:26 PM
 */
//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(name = "UNIQUE___USERS___EMAIL", columnNames = "EMAIL"))
//@Table(name = "USERS")
public abstract class User implements Serializable {


    @Column(name = "USERNAME")
    String username;
    @Column(name = "PASSWORD")
    String password;
    @Id
    @SequenceGenerator(name = "STUDENT_ID_SEQUENCE", sequenceName = "STUDENT_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_ID_SEQUENCE")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FORENAME")
    private String forename;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ForeignKey(name = "FK___USER___FILES")
    @JoinColumn(name = "USER_ID", nullable = true)
    private List<File> files;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

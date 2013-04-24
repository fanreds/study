package pl.edu.pk.domain;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/6/13
 * Time: 9:04 PM
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SPECIALIZATION")
public class Specialization implements Serializable {

    @Id
    @SequenceGenerator(name = "SPECIALIZATION_ID_SEQUENCE", sequenceName = "SPECIALIZATION_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALIZATION_ID_SEQUENCE")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "ACADEMY_ID")
    private Academy academy;
    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Group> groups;

    public Specialization() {
    }

    public Specialization(String name, Academy academy) {
        this.name = name;
        this.academy = academy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public List<Group> getGroups() {
        if (groups == null) {
            groups = new ArrayList<Group>();
        }
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Specialization that = (Specialization) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

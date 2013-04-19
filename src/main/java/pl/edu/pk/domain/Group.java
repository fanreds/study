package pl.edu.pk.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/16/13
 * Time: 6:57 PM
 */
@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @SequenceGenerator(name = "GROUPS_ID_SEQUENCE", sequenceName = "GROUPS_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUPS_ID_SEQUENCE")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Specialization specialization;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private List<Student> students;


    public Group() {
    }

    public Group(String name,Specialization specialization) {
        this.name=name;
        this.specialization=specialization;
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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<Student> getStudents() {
        if (students==null){
            students=new ArrayList<Student>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

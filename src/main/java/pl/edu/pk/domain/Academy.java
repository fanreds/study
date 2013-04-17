package pl.edu.pk.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/6/13
 * Time: 9:04 PM
 */
@Entity
@Table(name = "ACADEMY")
public class Academy implements Serializable {

    @Id
    @SequenceGenerator(name = "ACADEMY_ID_SEQUENCE", sequenceName = "ACADEMY_ID_SEQUENCE", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACADEMY_ID_SEQUENCE")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL)
    private List<Lecturer> lecturers;
    @OneToMany(mappedBy = "academy", cascade = CascadeType.ALL)
    private List<Specialization> specializations;

    public Academy(String name) {
        this.name = name;
    }

    public Academy() {
    }

    public Academy(String name, Address address) {
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Lecturer> getLecturers() {
        if (lecturers == null)
            lecturers = new ArrayList<Lecturer>();
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Specialization> getSpecializations() {
        if (specializations == null)
            specializations = new ArrayList<Specialization>();
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }
}

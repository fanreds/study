package pl.edu.pk.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/6/13
 * Time: 8:46 PM
 */
@Entity
@Table(name = "STUDENT")
//@AttributeOverride(name = "id", column = @Column(name = "STUDENT_ID"))
@PrimaryKeyJoinColumn(name="STUDENT_ID")
public class Student extends User {


    //or many to many
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;


    public Student() {
    }

    public Student(String name, String forename,String username,String password, Group group, Address address,Date birthDate) {
        super(username, password);
        setName(name);
        setForename(forename);
        setGroup(group);
        setAddress(address);
        setBirthday(birthDate);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

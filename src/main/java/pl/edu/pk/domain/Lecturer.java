package pl.edu.pk.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/6/13
 * Time: 8:48 PM
 */
@Entity
@Table(name = "LECTURER")
//@AttributeOverride(name = "id",column = @Column(name = "LECTURER_ID"))
@PrimaryKeyJoinColumn(name="LECTURER_ID")
public class Lecturer extends User {

    //or many to many
    @ManyToOne
    @JoinColumn(name = "ACADEMY_ID")
    private Academy academy;



    public Lecturer() {
    }

    public Lecturer(String name, String forename,String username,String password, Date birthday, Address address, Academy academy) {
        super(username,password);
        setName(name);
        setForename(forename);
        setBirthday(birthday);
        setAcademy(academy);
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }
}

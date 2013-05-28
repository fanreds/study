package pl.edu.pk;

import pl.edu.pk.domain.*;
import pl.edu.pk.security.SecurityGenerator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/12/13
 * Time: 3:55 PM
 */
@Startup
@Singleton
public class MySingleton {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {

        SecurityGenerator securityGenerator = new SecurityGenerator();
        Calendar calendar = Calendar.getInstance();

        final Address address = new Address("Warszawska", "Kraków", "11-333");
        final Academy academy = new Academy("Politechnika Krakowska", address);

        final Specialization inf = new Specialization("Informatyka", academy);
        final Specialization mat = new Specialization("Matematyka", academy);

        academy.getSpecializations().add(inf);
        academy.getSpecializations().add(mat);

        final Group groupINF1 = new Group("i15", inf);
        final Group groupINF2 = new Group("i25", inf);
        final Group groupMAT1 = new Group("i25", mat);
        final Group groupMAT2 = new Group("i35", mat);


        inf.getGroups().add(groupINF1);
        inf.getGroups().add(groupINF2);
        mat.getGroups().add(groupMAT1);
        mat.getGroups().add(groupMAT2);

        final Address address2 = new Address("Polna", "Gorlice", "15-333");
        calendar.set(1972, 11, 22);
        final User lecturer1 = new Lecturer("Sochal", "Janusz", "sochal", "ppp", calendar.getTime(), address2, academy);
        calendar.set(1979, 4, 4);
        final User lecturer2 = new Lecturer("Misznel", "Zbigniew", "misznel", "ppp", calendar.getTime(), address2, academy);
        calendar.set(1971, 1, 12);
        final User lecturer3 = new Lecturer("Nawrot", "Lucjan", "nawrot", "ppp", calendar.getTime(), address2, academy);

        securityGenerator.initRSA();
        securityGenerator.generateKeyPairDSA();
        lecturer1.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer1.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer2.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer2.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer3.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer3.setPublicKey(securityGenerator.getPublicKey());

        academy.getLecturers().add((Lecturer) lecturer1);
        academy.getLecturers().add((Lecturer) lecturer2);
        academy.getLecturers().add((Lecturer) lecturer3);

        final Address address1 = new Address("Lesna", "Tarnów", "13-333");
        calendar.set(1984, 2, 12);
        final User studentInf1 = new Student("Bugaj", "Paweł", "bugaj", "ppp", groupINF1, address1, calendar.getTime());
        calendar.set(1984, 2, 22);
        final User studentInf2 = new Student("Robak", "Michał", "robak", "ppp", groupINF2, address1, calendar.getTime());
        calendar.set(1985, 5, 22);
        final User studentInf3 = new Student("Siedlecki", "Piotr", "siedlecki", "ppp", groupINF1, address1, calendar.getTime());

        securityGenerator.generateKeyPairDSA();
        studentInf1.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf1.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf2.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf2.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf3.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf3.setPublicKey(securityGenerator.getPublicKey());


        calendar.set(1987, 4, 13);
        final User studentMat1 = new Student("Gomba", "Paweł", "gomba", "ppp", groupMAT1, address1, calendar.getTime());
        calendar.set(1986, 5, 2);
        final User studentMat2 = new Student("Winiarski", "Michał", "winiarski", "ppp", groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 6, 25);
        final User studentMat3 = new Student("Misztal", "Szymon", "misztal", "ppp", groupMAT2, address1, calendar.getTime());

        securityGenerator.generateKeyPairDSA();
        studentMat1.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat1.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat2.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat2.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat3.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat3.setPublicKey(securityGenerator.getPublicKey());


        groupINF1.getStudents().add((Student) studentInf1);
        groupINF1.getStudents().add((Student) studentInf3);
        groupINF2.getStudents().add((Student) studentInf2);

        groupMAT1.getStudents().add((Student) studentMat1);
        groupMAT2.getStudents().add((Student) studentMat2);
        groupMAT2.getStudents().add((Student) studentMat3);

        entityManager.persist(academy);
    }

}

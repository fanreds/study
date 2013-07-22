package pl.edu.pk;

import org.apache.commons.codec.digest.DigestUtils;
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

//    @PostConstruct
    public void init() {

        SecurityGenerator securityGenerator = new SecurityGenerator();
        Calendar calendar = Calendar.getInstance();

        final Address address = new Address("Warszawska", "Kraków", "11-333");
        final Academy academy = new Academy("Politechnika Krakowska", address);

        final Specialization inf = new Specialization("Informatyka", academy);
        final Specialization mat = new Specialization("Matematyka", academy);
        final Specialization eco = new Specialization("Ekonomia", academy);

        academy.getSpecializations().add(inf);
        academy.getSpecializations().add(mat);
        academy.getSpecializations().add(eco);

        final Group groupINF1 = new Group("i15", inf);
        final Group groupINF2 = new Group("i25", inf);
        final Group groupMAT1 = new Group("i25", mat);
        final Group groupMAT2 = new Group("i35", mat);
        final Group groupECO1 = new Group("i15", eco);


        inf.getGroups().add(groupINF1);
        inf.getGroups().add(groupINF2);
        mat.getGroups().add(groupMAT1);
        mat.getGroups().add(groupMAT2);
        eco.getGroups().add(groupECO1);

        final Address address2 = new Address("Polna", "Gorlice", "15-333");
        calendar.set(1972, 11, 22);
        final User lecturer1 = new Lecturer("Sochal", "Janusz", "sochal", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1979, 4, 4);
        final User lecturer2 = new Lecturer("Misznel", "Zbigniew", "misznel", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1971, 1, 12);
        final User lecturer3 = new Lecturer("Nawrot", "Lucjan", "nawrot", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1956, 2, 13);
        final User lecturer4 = new Lecturer("Soszyński", "Stanisław", "soszynski", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1963, 11, 7);
        final User lecturer5 = new Lecturer("Krupski", "Adam", "krupski", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1970, 10, 12);
        final User lecturer6 = new Lecturer("Lewandowski", "Ryszard", "lewandowski", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1971, 1, 12);
        final User lecturer7 = new Lecturer("Kołodziej", "Piotr", "kolodziej", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1974, 6, 17);
        final User lecturer8 = new Lecturer("Wrona", "Stefan", "wrona", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);
        calendar.set(1975, 12, 18);
        final User lecturer9 = new Lecturer("Gajda", "Tomasz", "gajda", DigestUtils.sha256Hex("changeit"), calendar.getTime(), address2, academy);


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
        securityGenerator.generateKeyPairDSA();
        lecturer4.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer4.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer5.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer5.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer6.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer6.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer7.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer7.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer8.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer8.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        lecturer9.setPrivateKey(securityGenerator.getPrivateKey());
        lecturer9.setPublicKey(securityGenerator.getPublicKey());


        academy.getLecturers().add((Lecturer) lecturer1);
        academy.getLecturers().add((Lecturer) lecturer2);
        academy.getLecturers().add((Lecturer) lecturer3);
        academy.getLecturers().add((Lecturer) lecturer4);
        academy.getLecturers().add((Lecturer) lecturer5);
        academy.getLecturers().add((Lecturer) lecturer6);
        academy.getLecturers().add((Lecturer) lecturer7);
        academy.getLecturers().add((Lecturer) lecturer8);
        academy.getLecturers().add((Lecturer) lecturer9);

        Address address1 = new Address("Lesna", "Tarnów", "13-333");
        calendar.set(1984, 2, 12);
        final User studentInf1 = new Student("Bugaj", "Paweł", "bugaj", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1984, 2, 22);
        final User studentInf2 = new Student("Robak", "Michał", "robak", DigestUtils.sha256Hex("changeit"), groupINF2, address1, calendar.getTime());
        calendar.set(1985, 5, 22);
        final User studentInf3 = new Student("Siedlecki", "Piotr", "siedlecki", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1987, 8, 16);
        final User studentInf4 = new Student("Jackowski", "Jacek", "jackowski", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1987, 3, 19);
        final User studentInf5 = new Student("Dychtoń", "Natalia", "dychton", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1987, 2, 12);
        final User studentInf6 = new Student("Sowa", "Ewa", "sowa", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1987, 7, 31);
        final User studentInf7 = new Student("Masło", "Tomasz", "maslo", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1987, 8, 5);
        final User studentInf8 = new Student("Stach", "Edward", "stach", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1987, 12, 24);
        final User studentInf9 = new Student("Jarosz", "Stefan", "jarosz", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());
        calendar.set(1985, 8, 12);
        final User studentInf10 = new Student("Miklas", "Marian", "miklas", DigestUtils.sha256Hex("changeit"), groupINF1, address1, calendar.getTime());

        address1 = new Address("Przewoska", "Żabno", "13-555");
        calendar.set(1990, 12, 18);
        final User studentInf11 = new Student("Marszałek", "Zofia", "marszalek", DigestUtils.sha256Hex("changeit"), groupMAT1, address1, calendar.getTime());
        calendar.set(1987, 3, 3);
        final User studentInf12 = new Student("Kmiotek", "Joanna", "kmiotek", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1992, 10, 29);
        final User studentInf13 = new Student("Porosło", "Kamila", "poroslo", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1980, 12, 24);
        final User studentInf14 = new Student("Bober", "Leszek", "bober", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1989, 6, 19);
        final User studentInf15 = new Student("Nasiadka", "Kamil", "nasiadka", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1979, 1, 23);
        final User studentInf16 = new Student("Sypek", "Klaudia", "sypek", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1988, 8, 27);
        final User studentInf17 = new Student("Kowalska", "Agnieszka", "kowalska", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 2, 12);
        final User studentInf18 = new Student("Rybak", "Anna", "rybak", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1986, 2, 8);
        final User studentInf19 = new Student("Kowal", "Jadwiga", "kowal", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1989, 10, 15);
        final User studentInf20 = new Student("Masztalski", "Aleksander", "masztalski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());

        securityGenerator.generateKeyPairDSA();
        studentInf1.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf1.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf2.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf2.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf3.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf3.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf4.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf4.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf5.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf5.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf6.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf6.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf7.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf7.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf8.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf8.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf9.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf9.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf10.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf10.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf11.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf11.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf12.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf12.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf13.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf13.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf14.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf14.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf15.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf15.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf16.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf16.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf17.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf17.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf18.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf18.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf19.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf19.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentInf20.setPrivateKey(securityGenerator.getPrivateKey());
        studentInf20.setPublicKey(securityGenerator.getPublicKey());

        address1 = new Address("Graniczna", "Gorlice", "13-444");
        calendar.set(1987, 4, 13);
        final User studentMat1 = new Student("Gomba", "Paweł", "gomba", DigestUtils.sha256Hex("changeit"), groupMAT1, address1, calendar.getTime());
        calendar.set(1986, 5, 2);
        final User studentMat2 = new Student("Winiarski", "Michał", "winiarski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 6, 25);
        final User studentMat3 = new Student("Misztal", "Szymon", "misztal", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 6, 25);
        final User studentMat4 = new Student("Dudczyk", "Sylwia", "dudczyk", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1984, 7, 20);
        final User studentMat5 = new Student("Nowak", "Eugeniusz", "nowak", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1991, 11, 25);
        final User studentMat6 = new Student("Warcholski", "Zenon", "Warcholski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 3, 17);
        final User studentMat7 = new Student("Szmigiel", "Szymon", "szmigiel", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1993, 9, 7);
        final User studentMat8 = new Student("Bogusz", "Maria", "bogusz", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1992, 1, 1);
        final User studentMat9 = new Student("Kukuła", "Jan", "kukula", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1990, 9, 11);
        final User studentMat10 = new Student("Lipski", "Marcin", "lipski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());

        address1 = new Address("Krakowska", "Dębica", "13-232");
        calendar.set(1991, 11, 8);
        final User studentMat11 = new Student("Sowiński", "Maciej", "sowinski", DigestUtils.sha256Hex("changeit"), groupMAT1, address1, calendar.getTime());
        calendar.set(1992, 3, 27);
        final User studentMat12 = new Student("Wojciechowska", "Aneta", "wojciechowska", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1990, 12, 18);
        final User studentMat13 = new Student("Okońska", "Urszula", "okonska", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 11, 14);
        final User studentMat14 = new Student("Maliniak", "Stanisław", "maliniak", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1989, 3, 17);
        final User studentMat15 = new Student("Kowalik", "Cezary", "kowalik", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1979, 10, 3);
        final User studentMat16 = new Student("Bogdański", "Dariusz", "bogdański", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1988, 9, 7);
        final User studentMat17 = new Student("Hajdo", "Patryk", "hajdo", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1981, 3, 12);
        final User studentMat18 = new Student("Widawski", "Filip", "widawski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1981, 4, 16);
        final User studentMat19 = new Student("Tymański", "Jakub", "tymanski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1989, 11, 4);
        final User studentMat20 = new Student("Oliwa", "Wojciech", "oliwa", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());

        securityGenerator.generateKeyPairDSA();
        studentMat1.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat1.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat2.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat2.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat3.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat3.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat4.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat4.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat5.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat5.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat6.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat6.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat7.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat7.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat8.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat8.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat9.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat9.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat10.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat10.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat11.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat11.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat12.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat12.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat13.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat13.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat14.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat14.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat15.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat15.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat16.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat16.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat17.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat17.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat18.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat18.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat19.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat19.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentMat20.setPrivateKey(securityGenerator.getPrivateKey());
        studentMat20.setPublicKey(securityGenerator.getPublicKey());


        address1 = new Address("Chopina", "Dąbrowa Tarnowska", "13-343");
        calendar.set(1990, 1, 8);
        final User studentEco1 = new Student("Jaroszek", "Wacław", "jaroszek", DigestUtils.sha256Hex("changeit"), groupMAT1, address1, calendar.getTime());
        calendar.set(1991, 8, 17);
        final User studentEco2 = new Student("Czarka", "Halina", "czarka", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1989, 2, 18);
        final User studentEco3 = new Student("Kołodziej", "Karolina", "kolodziej2", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1989, 11, 13);
        final User studentEco4 = new Student("Pawłowski", "Damian", "pawlowski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1987, 5, 12);
        final User studentEco5 = new Student("Kumięga", "Rafał", "kumiega", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1980, 19, 23);
        final User studentEco6 = new Student("Nytko", "Piotr", "nytko", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1992, 7, 17);
        final User studentEco7 = new Student("Furmański", "Paweł", "furmanski", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1984, 8, 19);
        final User studentEco8 = new Student("Kiełbasa", "Mariola", "kielbasa", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1986, 5, 10);
        final User studentEco9 = new Student("Rymczak", "Ewelina", "rymczak", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());
        calendar.set(1991, 12, 9);
        final User studentEco10 = new Student("Paw", "Zuzanna", "paw", DigestUtils.sha256Hex("changeit"), groupMAT2, address1, calendar.getTime());

        securityGenerator.generateKeyPairDSA();
        studentEco1.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco1.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco2.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco2.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco3.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco3.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco4.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco4.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco5.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco5.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco6.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco6.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco7.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco7.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco8.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco8.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco9.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco9.setPublicKey(securityGenerator.getPublicKey());
        securityGenerator.generateKeyPairDSA();
        studentEco10.setPrivateKey(securityGenerator.getPrivateKey());
        studentEco10.setPublicKey(securityGenerator.getPublicKey());

        groupINF1.getStudents().add((Student) studentInf1);
        groupINF1.getStudents().add((Student) studentInf2);
        groupINF1.getStudents().add((Student) studentInf3);
        groupINF1.getStudents().add((Student) studentInf4);
        groupINF1.getStudents().add((Student) studentInf5);
        groupINF1.getStudents().add((Student) studentInf6);
        groupINF1.getStudents().add((Student) studentInf7);
        groupINF1.getStudents().add((Student) studentInf8);
        groupINF1.getStudents().add((Student) studentInf9);
        groupINF1.getStudents().add((Student) studentInf10);

        groupINF2.getStudents().add((Student) studentInf11);
        groupINF2.getStudents().add((Student) studentInf12);
        groupINF2.getStudents().add((Student) studentInf13);
        groupINF2.getStudents().add((Student) studentInf14);
        groupINF2.getStudents().add((Student) studentInf15);
        groupINF2.getStudents().add((Student) studentInf16);
        groupINF2.getStudents().add((Student) studentInf17);
        groupINF2.getStudents().add((Student) studentInf18);
        groupINF2.getStudents().add((Student) studentInf19);
        groupINF2.getStudents().add((Student) studentInf20);

        groupMAT1.getStudents().add((Student) studentMat1);
        groupMAT1.getStudents().add((Student) studentMat2);
        groupMAT1.getStudents().add((Student) studentMat3);
        groupMAT1.getStudents().add((Student) studentMat4);
        groupMAT1.getStudents().add((Student) studentMat5);
        groupMAT1.getStudents().add((Student) studentMat6);
        groupMAT1.getStudents().add((Student) studentMat7);
        groupMAT1.getStudents().add((Student) studentMat8);
        groupMAT1.getStudents().add((Student) studentMat9);
        groupMAT1.getStudents().add((Student) studentMat10);

        groupMAT2.getStudents().add((Student) studentMat11);
        groupMAT2.getStudents().add((Student) studentMat12);
        groupMAT2.getStudents().add((Student) studentMat13);
        groupMAT2.getStudents().add((Student) studentMat14);
        groupMAT2.getStudents().add((Student) studentMat15);
        groupMAT2.getStudents().add((Student) studentMat16);
        groupMAT2.getStudents().add((Student) studentMat17);
        groupMAT2.getStudents().add((Student) studentMat18);
        groupMAT2.getStudents().add((Student) studentMat19);
        groupMAT2.getStudents().add((Student) studentMat20);

        groupECO1.getStudents().add((Student) studentEco1);
        groupECO1.getStudents().add((Student) studentEco2);
        groupECO1.getStudents().add((Student) studentEco3);
        groupECO1.getStudents().add((Student) studentEco4);
        groupECO1.getStudents().add((Student) studentEco5);
        groupECO1.getStudents().add((Student) studentEco6);
        groupECO1.getStudents().add((Student) studentEco7);
        groupECO1.getStudents().add((Student) studentEco8);
        groupECO1.getStudents().add((Student) studentEco9);
        groupECO1.getStudents().add((Student) studentEco10);

        entityManager.persist(academy);
    }

}
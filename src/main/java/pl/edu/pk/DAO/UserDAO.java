package pl.edu.pk.DAO;

import pl.edu.pk.business.EMProducer;
import pl.edu.pk.domain.*;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/10/13
 * Time: 10:25 AM
 */
@Stateless
public class UserDAO implements Serializable {

    @Inject
    private Instance<EMProducer> entityManagerInstance;

    public void save(User user) {
        entityManagerInstance.get().getEntityManager().merge(user);
    }

    public User getUser(String login) {
        User u = (User) entityManagerInstance.get().getEntityManager().createQuery("select u from User u where u.username = :name")
                .setParameter("name", login)
                .getSingleResult();
        return u;
    }

    public List<Specialization> getAllSpecializations(Academy academy){
        return entityManagerInstance.get().getEntityManager().createQuery("select s from Specialization s where s.academy=:academy")
                .setParameter("academy",academy)
                .getResultList();
    }

    public List<Group> getAllGroups(Specialization specialization){
        return entityManagerInstance.get().getEntityManager().createQuery("select g from Group g where g.specialization=:specialization")
                .setParameter("specialization",specialization)
                .getResultList();
    }

    public List<Student> getAllStudents(Group group){
        return entityManagerInstance.get().getEntityManager().createQuery("select g from Student g where g.group=:group")
                .setParameter("group",group)
                .getResultList();
    }

    public void refresh(Object o) {
        entityManagerInstance.get().getEntityManager().refresh(o);
    }
}

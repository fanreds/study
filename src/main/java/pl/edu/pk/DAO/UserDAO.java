package pl.edu.pk.DAO;

import pl.edu.pk.business.EMProducer;
import pl.edu.pk.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/10/13
 * Time: 10:25 AM
 */
@Stateless
public class UserDAO implements Serializable {

    @Inject
    private EMProducer entityManager;

    public void save(User user) {
        entityManager.getEntityManager().merge(user);
    }

    public User getUser(String login) {
        User u = (User) entityManager.getEntityManager().createQuery("select u from User u where u.username = :name")
                .setParameter("name", login)
                .getSingleResult();
        return u;
    }

    public void refresh(Object o) {
        entityManager.getEntityManager().refresh(o);
    }
}

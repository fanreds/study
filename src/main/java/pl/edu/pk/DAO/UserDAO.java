package pl.edu.pk.DAO;

import pl.edu.pk.EMProducer;
import pl.edu.pk.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
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

    public void refresh(Object o) {
        entityManagerInstance.get().getEntityManager().refresh(o);
    }
}

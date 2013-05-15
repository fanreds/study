package pl.edu.pk.DAO;

import pl.edu.pk.domain.*;
import pl.edu.pk.framework.Standalone;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 5/11/13
 * Time: 1:59 PM
 */
public class MessageDAO implements Serializable {

    @Standalone
    @Inject
    private EntityManager entityManager;

    public void persist(Message message) {
        entityManager.persist(message);
        entityManager.flush();
    }
    public void persist(MessageDeleted message) {
        entityManager.persist(message);
        entityManager.flush();
    }

    public List<Message> getInboxMessages(User user) {
        if (user instanceof Student) {
            return entityManager.createQuery("select m from Message m where (m.forAll=:bool or m.specialization=:spec " +
                    "or m.group=:group or m.recipient=:user)and m.sender!=:user and not exists (select d.message from MessageDeleted d where d.message=m) order by m.sentDate desc")
                    .setParameter("bool", true)
                    .setParameter("spec", ((Student) user).getGroup().getSpecialization())
                    .setParameter("group", ((Student) user).getGroup())
                    .setParameter("user", user)
                    .getResultList();
        } else {
            return entityManager.createQuery("select m from Message m where (m.forAll=:bool or m.recipient=:user)and m.sender!=:user and not exists" +
                    " (select d.message from MessageDeleted d where d.message=m) order by m.sentDate desc")
                    .setParameter("bool", true)
                    .setParameter("user", user)
                    .getResultList();
        }
    }
    public List<Message> getSentboxMessages(User user) {
            return entityManager.createQuery("select m from Message m where m.sender=:user order by m.sentDate desc")
                    .setParameter("user", user)
                    .getResultList();
    }

    public boolean update(Object o) {
        try {
            entityManager.merge(o);
            entityManager.flush();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

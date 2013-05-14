package pl.edu.pk.DAO;

import pl.edu.pk.domain.Message;
import pl.edu.pk.framework.Standalone;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

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

    public void persist(Message message){
        entityManager.persist(message);
        entityManager.flush();
    }
}

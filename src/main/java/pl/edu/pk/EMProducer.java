package pl.edu.pk;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/17/13
 * Time: 8:39 AM
 */
@Stateful
public class EMProducer implements Serializable {

    //this solution is against lazyexception (statefull and extended context)
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}

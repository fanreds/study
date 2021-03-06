package pl.edu.pk.business;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/17/13
 * Time: 8:39 AM
 */
@Stateless
public class EMProducer implements Serializable {

    //this solution is against lazyexception (statefull and extended context)
//    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private TransactionSynchronizationRegistry registry;

    public EntityManager getEntityManager() {
        return entityManager;
    }

//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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

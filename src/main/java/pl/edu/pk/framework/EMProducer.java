package pl.edu.pk.framework;

import org.jboss.solder.core.ExtensionManaged;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/17/13
 * Time: 8:39 AM
 */
public class EMProducer implements Serializable {

    @Standalone
    @ExtensionManaged
    @Produces
    @PersistenceUnit
//    @ConversationScoped
    private EntityManagerFactory standaloneEMF;

}

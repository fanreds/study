package pl.edu.pk.DAO;

import org.jboss.seam.transaction.Transactional;
import pl.edu.pk.business.CurrentUserManager;
import pl.edu.pk.business.Refresh;
import pl.edu.pk.domain.*;
import pl.edu.pk.framework.Standalone;

import javax.enterprise.context.Conversation;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/10/13
 * Time: 10:25 AM
 */

public class UserDAO implements Serializable {

    @Standalone
    @Inject
    private EntityManager entityManager;
    @Inject
    private Conversation conversation;
    @Inject
    private CurrentUserManager currentUserManager;
    @Refresh
    @Inject
    private Event<User> userEvent;

    public void save(User user) {
        entityManager.merge(user);
    }

    public User getUser(String login) {
        User u = (User) entityManager.createQuery("select u from User u where u.username = :name")
                .setParameter("name", login)
                .getSingleResult();
        return u;
    }

    public List<File> getSharedFiles(User user) {
        return entityManager.createQuery("select distinct f from File f left join f.fileAccess.users u where (f.fileAccess.shareAll=:bool or " +
                "f.fileAccess.specialization=:specialization or f.fileAccess.group=:group or u=:user)and f.user!=:user" )
                .setParameter("specialization", ((Student) user).getGroup().getSpecialization())
                .setParameter("group", ((Student) user).getGroup())
                .setParameter("user", user)
                .setParameter("bool",true)
                .getResultList();
//        return entityManager.createQuery("select distinct f from File f left join f.fileAccess.users u where (f.fileAccess.shareAll=:bool or " +
//                "f.fileAccess.specialization=:specialization or f.fileAccess.group=:group or u=:user) and f not member of " +
//                "(select of from User join fetch o.files of where o=:user and of.shareAll=:bool)" )
//                .setParameter("specialization", ((Student) user).getGroup().getSpecialization())
//                .setParameter("group", ((Student) user).getGroup())
//                .setParameter("user", user)
//                .setParameter("bool",true)
//                .getResultList();
    }

    public User getUser(User user) {
        return entityManager.find(User.class, user.getId());
    }

    public List<Specialization> getAllSpecializations(Academy academy) {
        return entityManager.createQuery("select s from Specialization s where s.academy=:academy")
                .setParameter("academy", academy)
                .getResultList();
    }

    public List<Group> getAllGroups(Specialization specialization) {
        return entityManager.createQuery("select g from Group g where g.specialization=:specialization")
                .setParameter("specialization", specialization)
                .getResultList();
    }

    public List<Student> getAllStudents(Group group) {
        return entityManager.createQuery("select g from Student g where g.group=:group")
                .setParameter("group", group)
                .getResultList();
    }

    public List<Lecturer> getAllLecturers() {
        return entityManager.createQuery("select g from Lecturer g")
                .getResultList();
    }

    public void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    @Transactional
    public boolean update(Object o) {
        try {
//            initConversation();
            entityManager.merge(o);
            entityManager.flush();
            if (o instanceof User || o instanceof FileAccess) {
                userEvent.fire((User) o);
            }
//            endConversation();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

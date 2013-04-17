package pl.edu.pk.business;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.PostLoggedOutEvent;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.domain.User;
import pl.edu.pk.user.CurrentUser;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/12/13
 * Time: 4:51 PM
 */
@SessionScoped
public class CurrentUserManager implements Serializable {

    private User user;
    @Inject
    private Instance<Identity> identityInstance;
    @Inject
    private UserDAO userDAO;

    @CurrentUser
    @Produces
    public User getUser() {
        final Identity identity = identityInstance.get();
        if (!identity.isLoggedIn()) {
            user = null;
            return user;
        }
        String username = identity.getUser().getId();
        if (user != null) {
            if (!user.getUsername().equals(username)) {
                user = null;
                return user;
            }
        } else {
            user = userDAO.getUser(username);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void onLogout(@Observes final PostLoggedOutEvent event)
    {
        user = null;
    }

}

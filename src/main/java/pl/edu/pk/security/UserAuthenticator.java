package pl.edu.pk.security;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;
import pl.edu.pk.DAO.UserDAO;
import pl.edu.pk.domain.User;
import pl.edu.pk.view.CurrentUserManager;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/10/13
 * Time: 11:12 AM
 */
public class UserAuthenticator extends BaseAuthenticator {

    @Inject
    private Identity identity;
    @Inject
    private Credentials credentials;
    @Inject
    private UserDAO userDAO;
    @Inject
    private CurrentUserManager currentUserManager;
    @Inject
    private Messages messages;

    @Override
    public void authenticate() {
        final User user = userDAO.getUser(credentials.getUsername());

        String password = ((PasswordCredential) credentials.getCredential()).getValue();

        if (user != null && user.getPassword().equals(password)) {
            setStatus(AuthenticationStatus.SUCCESS);
            setUser(new SimpleUser(user.getUsername()));
            currentUserManager.setUser(user);
        } else {
            messages.error(new BundleKey("messages", "pl.edu.pl.edu.pk.security.UserAuthenticator.loginFailed"));
        }

    }
}

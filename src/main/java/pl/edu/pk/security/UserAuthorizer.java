package pl.edu.pk.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;
import pl.edu.pk.security.annotations.Authenticated;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/16/13
 * Time: 1:21 PM
 */

public class UserAuthorizer {

    @Secures
    @Authenticated
    public boolean isAuthenticated(Identity identity)
    {
        return identity.isLoggedIn();
    }
}

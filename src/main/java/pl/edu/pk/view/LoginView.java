package pl.edu.pk.view;

import org.jboss.seam.security.Identity;
import pl.edu.pk.business.CurrentUserManager;

import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 3/9/13
 * Time: 10:49 PM
 */
@Named
@ViewScoped
public class LoginView implements Serializable {

    @Inject
    private Instance<Identity> identityInstance;

    @Inject
    private CurrentUserManager currentUserManager;

    public String getUsername() {
        return currentUserManager.getUser().getUsername();
    }

    public String logout(){
        identityInstance.get().logout();
        return "logout";
    }

}

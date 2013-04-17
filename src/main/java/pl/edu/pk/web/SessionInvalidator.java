package pl.edu.pk.web;

import org.jboss.seam.security.events.PostLoggedOutEvent;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/16/13
 * Time: 4:46 PM
 */
@RequestScoped
public class SessionInvalidator {

    @Inject
    private HttpServletRequest httpRequest;


    public void onSessionInvalidate(@Observes final PostLoggedOutEvent event){
          httpRequest.getSession().invalidate();
    }

}

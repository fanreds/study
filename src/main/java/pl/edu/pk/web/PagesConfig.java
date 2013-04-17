package pl.edu.pk.web;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import pl.edu.pk.security.annotations.AccessDenied;
import pl.edu.pk.security.annotations.Authenticated;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/16/13
 * Time: 12:35 PM
 */
@ViewConfig
public interface PagesConfig {

    static enum Pages{
        @AccessDenied @RestrictAtPhase(PhaseIdType.RESTORE_VIEW) @ViewPattern("/layout/*")
        LAYOUTS,
        @Authenticated  @ViewPattern("/view/private/*") @LoginView("/view/login.xhtml") @FacesRedirect @RestrictAtPhase(
                {PhaseIdType.RESTORE_VIEW, PhaseIdType.INVOKE_APPLICATION, PhaseIdType.PROCESS_VALIDATIONS, PhaseIdType.UPDATE_MODEL_VALUES,
                        PhaseIdType.APPLY_REQUEST_VALUES, PhaseIdType.RENDER_RESPONSE})
        USER
    }
}

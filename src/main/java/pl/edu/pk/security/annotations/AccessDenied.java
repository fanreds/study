package pl.edu.pk.security.annotations;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.security.annotations.SecurityBindingType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/16/13
 * Time: 11:46 AM
 */
@RestrictAtPhase(
        {PhaseIdType.RESTORE_VIEW, PhaseIdType.APPLY_REQUEST_VALUES, PhaseIdType.INVOKE_APPLICATION, PhaseIdType.PROCESS_VALIDATIONS, PhaseIdType.RENDER_RESPONSE,
                PhaseIdType.UPDATE_MODEL_VALUES})
@SecurityBindingType
@Target({TYPE, METHOD, PARAMETER, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessDenied {

}
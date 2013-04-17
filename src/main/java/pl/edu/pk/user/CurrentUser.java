package pl.edu.pk.user;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/16/13
 * Time: 2:49 PM
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface CurrentUser {

}


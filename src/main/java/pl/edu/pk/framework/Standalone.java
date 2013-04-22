package pl.edu.pk.framework;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/22/13
 * Time: 5:16 PM
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface Standalone {

}
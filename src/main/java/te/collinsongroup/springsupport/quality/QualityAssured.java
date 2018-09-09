package te.collinsongroup.springsupport.quality;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a controller endpoint as quality assured, removing the feature switch.
 *
 * @author alastair
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface QualityAssured {

}

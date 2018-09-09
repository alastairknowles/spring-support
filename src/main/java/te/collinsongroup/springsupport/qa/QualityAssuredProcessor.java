package te.collinsongroup.springsupport.qa;

import com.google.common.base.Joiner;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static org.apache.commons.lang3.ArrayUtils.contains;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

/**
 * Provides generic capability to guard endpoints that have not been quality assured yet.
 *
 * @author alastair
 */
@Aspect
public class QualityAssuredProcessor {

    private static final String PRODUCTION_PROFILE = "prod";

    private final boolean guardEndpoints;

    public QualityAssuredProcessor(String[] activeProfiles) {
        this.guardEndpoints = contains(activeProfiles, PRODUCTION_PROFILE);
    }

    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void beforeControllerInvocation(final JoinPoint joinPoint) {
        if (guardEndpoints) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            if (method.getAnnotation(QualityAssured.class) == null) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();

                throw new QualityAssuredException(
                        RequestMethod.valueOf(request.getMethod()),
                        Joiner.on("?").skipNulls().join(request.getRequestURI(), request.getQueryString()));
            }
        }
    }

}

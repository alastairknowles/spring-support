package te.collinsongroup.springsupport.qa;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.lang.String.format;

/**
 * Exception thrown when calling an endpoint that is not yet quality assured.
 *
 * @author alastair
 */
@Getter
public class QualityAssuredException extends RuntimeException {

    private static final String MESSAGE = "%s: %s awaiting quality assurance";

    private final RequestMethod method;

    private final String path;

    QualityAssuredException(RequestMethod method, String path) {
        super(format(MESSAGE, method, path));
        this.method = method;
        this.path = path;
    }

    @Override
    public String toString() {
        return format(MESSAGE, method, path);
    }

}

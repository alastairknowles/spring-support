package te.collinsongroup.springsupport.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import te.collinsongroup.springsupport.quality.QualityAssuredException;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

/**
 * Provides generic exception handling capability, mapping thrown exceptions to error responses.
 *
 * @author alastair
 */
@RestControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = getLogger(BaseExceptionHandler.class);

    @SuppressWarnings("unused")
    @ExceptionHandler(QualityAssuredException.class)
    private ResponseEntity<Object> handleQualityAssuredException(QualityAssuredException exception, WebRequest request) {
        String description = exception.toString();
        LOGGER.warn(description);

        return handleExceptionInternal(exception,
                ErrorResponseDto.builder().status(NOT_IMPLEMENTED).description(description).build(),
                new HttpHeaders(), NOT_IMPLEMENTED, request);
    }

}

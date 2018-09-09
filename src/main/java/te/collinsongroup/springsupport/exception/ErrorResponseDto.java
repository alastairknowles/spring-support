package te.collinsongroup.springsupport.exception;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Base DTO for passing around error responses from rest controllers.
 *
 * @author alastair
 */
@Value
@Builder
public class ErrorResponseDto {

    private HttpStatus status;

    private String description;

    private List<ErrorItemDto> errorItems;

    /**
     * Base DTO for passing around error items, for example field validation errors.
     *
     * @author alastair
     */
    @Value
    @Builder
    public static class ErrorItemDto {

        private String key;

        private String description;

    }

}

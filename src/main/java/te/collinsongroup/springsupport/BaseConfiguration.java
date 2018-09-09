package te.collinsongroup.springsupport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import te.collinsongroup.springsupport.exception.BaseExceptionHandler;
import te.collinsongroup.springsupport.qa.QualityAssuredProcessor;

/**
 * Provides the base configuration for spring boot services.
 *
 * @author alastair
 */
@EnableAspectJAutoProxy
public class BaseConfiguration {

    private final String[] activeProfiles;

    public BaseConfiguration(Environment environment) {
        this.activeProfiles = environment.getActiveProfiles();
    }

    @Bean
    public BaseExceptionHandler baseExceptionHandler() {
        return new BaseExceptionHandler();
    }

    @Bean
    public QualityAssuredProcessor qualityAssuredProcessor() {
        return new QualityAssuredProcessor(activeProfiles);
    }

}

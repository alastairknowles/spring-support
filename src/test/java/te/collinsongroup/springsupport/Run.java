package te.collinsongroup.springsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(BaseConfiguration.class)
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class);
    }

}

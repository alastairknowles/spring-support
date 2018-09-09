package te.collinsongroup.springsupport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import te.collinsongroup.springsupport.quality.QualityAssured;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@SuppressWarnings("unused")
public class TestController {

    @GetMapping("/get-mapping")
    public Map<String, Object> getMapping() {
        return singletonMap("key", "value");
    }

    @QualityAssured
    @GetMapping("/get-mapping-assured")
    public Map<String, Object> getMappingAssured() {
        return singletonMap("key", "value");
    }

}

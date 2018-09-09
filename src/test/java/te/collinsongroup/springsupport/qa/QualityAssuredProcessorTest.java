package te.collinsongroup.springsupport.qa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import te.collinsongroup.springsupport.exception.ErrorResponseDto;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
public class QualityAssuredProcessorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void error_whenGetMappingNotQualityAssured() throws Exception {
        ErrorResponseDto response =
                objectMapper.readValue(
                        mockMvc.perform(
                                get("/get-mapping")
                                        .contentType(APPLICATION_JSON))
                                .andExpect(status().isNotImplemented())
                                .andReturn().getResponse().getContentAsString(),
                        ErrorResponseDto.class);

        assertEquals(NOT_IMPLEMENTED, response.getStatus());
        assertEquals("GET: /get-mapping awaiting quality assurance", response.getDescription());
    }

    @Test
    public void success_WhenGetMappingQualityAssured() throws Exception {
        Map<String, Object> response =
                objectMapper.readValue(
                        mockMvc.perform(
                                get("/get-mapping-assured")
                                        .contentType(APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn().getResponse().getContentAsString(),
                        new TypeReference<Map<String, Object>>() {
                        });

        assertThat(response).containsKeys("key").containsValues("value");
    }

}

package project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import project.text.TextProcessingRequest;
import project.text.TextProcessingResult;
import project.text.processor.ProcessingOption;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test() throws Exception {
        String url = "http://localhost:%d/processing/%s";
        String text = "abc XYZ 123\t";
        ProcessingOption[] options = {ProcessingOption.REMOVE_WHITE_SPACES, ProcessingOption.TO_UPPER_CASE};

        TextProcessingRequest request = new TextProcessingRequest();
        request.setText(text);
        request.setOptions(options);

        String id = new ObjectMapper().readValue(
                restTemplate.postForObject(String.format(url, port, ""), request, String.class),
                new TypeReference<Map<String, String>>() {}
        ).get("id");

        long startTime = System.currentTimeMillis();
        ResponseEntity<TextProcessingResult> result;
        do {
            if (System.currentTimeMillis() - startTime > 5000) {
                throw new Exception("failed");
            }
            result = restTemplate.getForEntity(String.format(url, port, id), TextProcessingResult.class);
        } while (result.getStatusCodeValue() != 200);

        assertEquals(new TextProcessingResult(UUID.fromString(id), options, text, "ABCXYZ123"), result.getBody());
    }

}

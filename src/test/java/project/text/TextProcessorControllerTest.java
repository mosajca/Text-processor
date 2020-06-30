package project.text;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import project.text.processor.ProcessingOption;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TextProcessorController.class)
class TextProcessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextProcessingResultRepository textProcessingResultRepository;

    @MockBean
    private TextProcessorService textProcessorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testProcess() throws Exception {
        TextProcessingRequest request = new TextProcessingRequest();
        request.setText("abc");
        request.setOptions(ProcessingOption.values());

        MvcResult mvcResult = mockMvc.perform(post("/processing")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andReturn();

        Map<String, String> map = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<Map<String, String>>() {
                }
        );
        assertEquals(1, map.size());
        assertTrue(map.containsKey("id"));

        verify(textProcessorService).process(UUID.fromString(map.get("id")), request);
    }

    @Test
    void testProcessWithoutText() throws Exception {
        TextProcessingRequest request = new TextProcessingRequest();
        request.setOptions(ProcessingOption.values());

        MvcResult mvcResult = mockMvc.perform(post("/processing")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testProcessWithoutOptions() throws Exception {
        TextProcessingRequest request = new TextProcessingRequest();
        request.setText("abc");

        MvcResult mvcResult = mockMvc.perform(post("/processing")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testProcessWithoutTextAndOptions() throws Exception {
        TextProcessingRequest request = new TextProcessingRequest();

        MvcResult mvcResult = mockMvc.perform(post("/processing")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetResults() throws Exception {
        List<TextProcessingResult> results = new ArrayList<>();
        results.add(new TextProcessingResult(
                UUID.randomUUID(),
                new ProcessingOption[]{ProcessingOption.TO_UPPER_CASE},
                "abc",
                "ABC"
        ));
        results.add(new TextProcessingResult(
                UUID.randomUUID(),
                new ProcessingOption[]{ProcessingOption.REMOVE_WHITE_SPACES},
                "abc XYZ",
                "abcXYZ"
        ));

        when(textProcessingResultRepository.findAll()).thenReturn(results);

        MvcResult mvcResult = mockMvc.perform(get("/processing"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(objectMapper.writeValueAsString(results), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetResultsEmpty() throws Exception {
        when(textProcessingResultRepository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(get("/processing"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("[]", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetResult() throws Exception {
        UUID id = UUID.randomUUID();
        TextProcessingResult result = new TextProcessingResult(
                id,
                new ProcessingOption[]{ProcessingOption.TO_LOWER_CASE},
                "ABC",
                "abc"
        );

        when(textProcessingResultRepository.findById(id)).thenReturn(Optional.of(result));

        MvcResult mvcResult = mockMvc.perform(get("/processing/" + id.toString()))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(objectMapper.writeValueAsString(result), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetResultNotFound() throws Exception {
        when(textProcessingResultRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(get("/processing/" + UUID.randomUUID().toString()))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetResultInvalidUUID() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/processing/1"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

}
package project.text;

import org.junit.jupiter.api.Test;
import project.text.processor.ProcessingOption;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TextProcessorServiceTest {

    @Test
    void test() {
        TextProcessingResultRepository textProcessingResultRepository = mock(TextProcessingResultRepository.class);
        TextProcessorService textProcessorService = new TextProcessorService(textProcessingResultRepository);
        UUID id = UUID.randomUUID();
        String text = "abc XYZ 123\t";
        ProcessingOption[] options = new ProcessingOption[]{
                ProcessingOption.REMOVE_WHITE_SPACES,
                ProcessingOption.TO_UPPER_CASE
        };
        TextProcessingRequest textProcessingRequest = new TextProcessingRequest();
        textProcessingRequest.setText(text);
        textProcessingRequest.setOptions(options);

        textProcessorService.process(id, textProcessingRequest);

        verify(textProcessingResultRepository).save(new TextProcessingResult(id, options, text, "ABCXYZ123"));
    }

}
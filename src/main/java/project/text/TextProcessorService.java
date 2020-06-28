package project.text;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import project.text.processor.ProcessingOption;

import java.util.UUID;

@Service
public class TextProcessorService {

    private final TextProcessingResultRepository textProcessingResultRepository;

    public TextProcessorService(TextProcessingResultRepository textProcessingResultRepository) {
        this.textProcessingResultRepository = textProcessingResultRepository;
    }

    @Async
    public void process(UUID id, TextProcessingRequest request) {
        String text = request.getText();
        ProcessingOption[] options = request.getOptions();

        textProcessingResultRepository.save(new TextProcessingResult(
                id,
                options,
                text,
                ProcessingOption.createProcessor(options).process(text)
        ));
    }

}

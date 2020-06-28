package project.text;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import project.text.processor.ProcessingOption;

import java.util.UUID;

public class TextProcessingResult implements Persistable<UUID> {

    @Id
    private final UUID id;

    private final ProcessingOption[] options;

    private final String originalText;

    private final String resultText;

    public TextProcessingResult(UUID id, ProcessingOption[] options, String originalText, String resultText) {
        this.id = id;
        this.options = options;
        this.originalText = originalText;
        this.resultText = resultText;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public ProcessingOption[] getOptions() {
        return options;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getResultText() {
        return resultText;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return true;
    }

}

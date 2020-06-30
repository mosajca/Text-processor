package project.text;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import project.text.processor.ProcessingOption;

import java.util.Arrays;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextProcessingResult that = (TextProcessingResult) o;
        return Objects.equals(id, that.id) &&
                Arrays.equals(options, that.options) &&
                Objects.equals(originalText, that.originalText) &&
                Objects.equals(resultText, that.resultText);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, originalText, resultText);
        result = 31 * result + Arrays.hashCode(options);
        return result;
    }

}

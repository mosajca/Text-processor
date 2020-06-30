package project.text;

import project.text.processor.ProcessingOption;

import java.util.Arrays;
import java.util.Objects;

public class TextProcessingRequest {

    private String text;

    private ProcessingOption[] options;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProcessingOption[] getOptions() {
        return options;
    }

    public void setOptions(ProcessingOption[] options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextProcessingRequest that = (TextProcessingRequest) o;
        return Objects.equals(text, that.text) &&
                Arrays.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(text);
        result = 31 * result + Arrays.hashCode(options);
        return result;
    }

}

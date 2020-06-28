package project.text;

import project.text.processor.ProcessingOption;

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

}

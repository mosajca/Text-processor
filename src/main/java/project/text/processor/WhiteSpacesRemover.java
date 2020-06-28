package project.text.processor;

class WhiteSpacesRemover extends ProcessorDecorator {

    public WhiteSpacesRemover(Processor processor) {
        super(processor);
    }

    @Override
    public String process(String text) {
        return super.process(text).replaceAll("\\s", "");
    }

}

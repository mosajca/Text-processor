package project.text.processor;

class UpperCase extends ProcessorDecorator {

    public UpperCase(Processor processor) {
        super(processor);
    }

    @Override
    public String process(String text) {
        return super.process(text).toUpperCase();
    }

}

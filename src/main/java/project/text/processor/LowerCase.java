package project.text.processor;

class LowerCase extends ProcessorDecorator {

    public LowerCase(Processor processor) {
        super(processor);
    }

    @Override
    public String process(String text) {
        return super.process(text).toLowerCase();
    }

}

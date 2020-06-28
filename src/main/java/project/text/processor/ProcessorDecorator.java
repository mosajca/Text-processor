package project.text.processor;

abstract class ProcessorDecorator implements Processor {

    private final Processor processor;

    public ProcessorDecorator(Processor processor) {
        this.processor = processor;
    }

    @Override
    public String process(String text) {
        return processor.process(text);
    }

}

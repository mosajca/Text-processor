package project.text.processor;

public enum ProcessingOption {

    TO_LOWER_CASE {
        @Override
        Processor createProcessor(Processor processor) {
            return new LowerCase(processor);
        }
    },
    TO_UPPER_CASE {
        @Override
        Processor createProcessor(Processor processor) {
            return new UpperCase(processor);
        }
    },
    REMOVE_WHITE_SPACES {
        @Override
        Processor createProcessor(Processor processor) {
            return new WhiteSpacesRemover(processor);
        }
    },
    ROT13 {
        @Override
        Processor createProcessor(Processor processor) {
            return new ROT13(processor);
        }
    };

    abstract Processor createProcessor(Processor processor);

    public static Processor createProcessor(ProcessingOption[] options) {
        Processor processor = new BasicProcessor();
        for (ProcessingOption option : options) {
            processor = option.createProcessor(processor);
        }
        return processor;
    }

}

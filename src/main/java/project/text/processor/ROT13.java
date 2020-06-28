package project.text.processor;

class ROT13 extends ProcessorDecorator {

    private final static int LOWER_CASE_START = 'a';
    private final static int LOWER_CASE_END = 'z';

    private final static int UPPER_CASE_START = 'A';
    private final static int UPPER_CASE_END = 'Z';

    public ROT13(Processor processor) {
        super(processor);
    }

    @Override
    public String process(String text) {
        char[] chars = super.process(text).toCharArray();

        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (isLowerCase(c)) {
                chars[i] = rot13(c, LOWER_CASE_END);
            } else if (isUpperCase(c)) {
                chars[i] = rot13(c, UPPER_CASE_END);
            }
        }

        return String.valueOf(chars);
    }

    private boolean isLowerCase(char c) {
        return c >= LOWER_CASE_START && c <= LOWER_CASE_END;
    }

    private boolean isUpperCase(char c) {
        return c >= UPPER_CASE_START && c <= UPPER_CASE_END;
    }

    private char rot13(char c, int max) {
        c += 13;
        if (c > max) {
            c -= 26;
        }
        return c;
    }

}

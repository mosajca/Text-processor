package project.text.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicProcessorTest {

    @Test
    void test() {
        BasicProcessor processor = new BasicProcessor();
        String text = "abc XYZ 123\t";

        assertEquals(text, processor.process(text));
    }

}
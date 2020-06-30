package project.text.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UpperCaseTest {

    @Test
    void test() {
        Processor processor = mock(Processor.class);
        when(processor.process(anyString())).then(returnsFirstArg());
        UpperCase upperCase = new UpperCase(processor);
        String text = "abc XYZ 123\t";

        assertEquals("ABC XYZ 123\t", upperCase.process(text));
        verify(processor).process(text);
        verifyNoMoreInteractions(processor);
    }

}
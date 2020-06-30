package project.text.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LowerCaseTest {

    @Test
    void test() {
        Processor processor = mock(Processor.class);
        when(processor.process(anyString())).then(returnsFirstArg());
        LowerCase lowerCase = new LowerCase(processor);
        String text = "abc XYZ 123\t";

        assertEquals("abc xyz 123\t", lowerCase.process(text));
        verify(processor).process(text);
        verifyNoMoreInteractions(processor);
    }

}
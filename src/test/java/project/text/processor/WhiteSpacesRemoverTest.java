package project.text.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class WhiteSpacesRemoverTest {

    @Test
    void test() {
        Processor processor = mock(Processor.class);
        when(processor.process(anyString())).then(returnsFirstArg());
        WhiteSpacesRemover whiteSpacesRemover = new WhiteSpacesRemover(processor);
        String text = "  abc XYZ 123\t";

        assertEquals("abcXYZ123", whiteSpacesRemover.process(text));
        verify(processor).process(text);
        verifyNoMoreInteractions(processor);
    }

}
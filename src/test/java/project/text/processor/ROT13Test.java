package project.text.processor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ROT13Test {

    @Mock
    private Processor processor;

    private ROT13 rot13;

    @BeforeEach
    void setUp() {
        when(processor.process(anyString())).then(returnsFirstArg());
        rot13 = new ROT13(processor);
    }

    @Test
    void testLowerCaseLetters() {
        String text = "abcdefghijklmnopqrstuvwxyz";
        String expected = "nopqrstuvwxyzabcdefghijklm";

        assertEquals(expected, rot13.process(text));
        verify(processor).process(text);
    }

    @Test
    void testUpperCaseLetters() {
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "NOPQRSTUVWXYZABCDEFGHIJKLM";

        assertEquals(expected, rot13.process(text));
        verify(processor).process(text);
    }

    @Test
    void testOther() {
        String text = "`1234567890-= ~!@#$%^&*()_+\t[]\\{}|;':\",./<>?";

        assertEquals(text, rot13.process(text));
        verify(processor).process(text);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(processor);
    }

}
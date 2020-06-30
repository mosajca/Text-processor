package project.text.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProcessingOptionTest {

    @Mock
    private Processor processor;

    @Test
    void testToLowerCase() {
        assertEquals(LowerCase.class, ProcessingOption.TO_LOWER_CASE.createProcessor(processor).getClass());
    }

    @Test
    void testToUpperCase() {
        assertEquals(UpperCase.class, ProcessingOption.TO_UPPER_CASE.createProcessor(processor).getClass());
    }

    @Test
    void testRemoveWhiteSpaces() {
        assertEquals(WhiteSpacesRemover.class, ProcessingOption.REMOVE_WHITE_SPACES.createProcessor(processor).getClass());
    }

    @Test
    void testROT13() {
        assertEquals(ROT13.class, ProcessingOption.ROT13.createProcessor(processor).getClass());
    }

    @Test
    void testCreateProcessorLowerCaseWithoutWhiteSpaces() {
        String text = "abc XYZ 123\t";
        ProcessingOption[] options = {
                ProcessingOption.TO_LOWER_CASE,
                ProcessingOption.REMOVE_WHITE_SPACES
        };
        assertEquals("abcxyz123", ProcessingOption.createProcessor(options).process(text));
    }

    @Test
    void testCreateProcessorUpperCaseROT13() {
        String text = "abc XYZ 123\t";
        ProcessingOption[] options = {
                ProcessingOption.TO_UPPER_CASE,
                ProcessingOption.ROT13
        };
        assertEquals("NOP KLM 123\t", ProcessingOption.createProcessor(options).process(text));
    }

}
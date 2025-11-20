package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ParenthesesValidatorTest {

    @Test
    void testCorrectBrackets() {
        ParenthesesValidator validator = new ParenthesesValidator();
        List<String> tokens = List.of("(", "2", "+", "3", ")");
        assertDoesNotThrow(() -> validator.validate(tokens));
    }

    @Test
    void testMissingClosingBracket() {
        ParenthesesValidator validator = new ParenthesesValidator();
        List<String> tokens = List.of("(", "2", "+", "3");

        assertThrows(RuntimeException.class, () -> validator.validate(tokens));
    }

    @Test
    void testExtraClosingBracket() {
        ParenthesesValidator validator = new ParenthesesValidator();
        List<String> tokens = List.of("2", ")", "+", "3");

        assertThrows(RuntimeException.class, () -> validator.validate(tokens));
    }
}

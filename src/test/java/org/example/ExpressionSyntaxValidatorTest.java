package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionSyntaxValidatorTest {

    @Test
    void testCorrectExpression() {
        ExpressionSyntaxValidator validator = new ExpressionSyntaxValidator();
        List<String> tokens = List.of("2", "+", "3", "*", "4");

        assertDoesNotThrow(() -> validator.validate(tokens));
    }

    @Test
    void testDoubleOperator() {
        ExpressionSyntaxValidator validator = new ExpressionSyntaxValidator();
        List<String> tokens = List.of("2", "+", "+", "3");

        assertThrows(RuntimeException.class, () -> validator.validate(tokens));
    }

    @Test
    void testStartsWithOperator() {
        ExpressionSyntaxValidator validator = new ExpressionSyntaxValidator();
        List<String> tokens = List.of("*", "2", "+", "3");

        assertThrows(RuntimeException.class, () -> validator.validate(tokens));
    }

    @Test
    void testEmptyBrackets() {
        ExpressionSyntaxValidator validator = new ExpressionSyntaxValidator();
        List<String> tokens = List.of("(", ")");

        assertThrows(RuntimeException.class, () -> validator.validate(tokens));
    }
}

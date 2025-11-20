package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionRPNConverterTest {

    @Test
    void testSimpleRPN() {
        ExpressionRPNConverter converter = new ExpressionRPNConverter();
        List<String> tokens = List.of("2", "+", "3");
        List<String> rpn = converter.toRPN(tokens);

        assertEquals(List.of("2", "3", "+"), rpn);
    }

    @Test
    void testWithBrackets() {
        ExpressionRPNConverter converter = new ExpressionRPNConverter();
        List<String> tokens = List.of("(", "2", "+", "3", ")", "*", "4");
        List<String> rpn = converter.toRPN(tokens);

        assertEquals(List.of("2", "3", "+", "4", "*"), rpn);
    }
}

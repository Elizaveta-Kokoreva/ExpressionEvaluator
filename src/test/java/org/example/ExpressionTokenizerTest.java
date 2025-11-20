package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTokenizerTest {

    @Test
    void testSimpleExpression() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer();
        List<String> tokens = tokenizer.tokenize("2+3");

        assertEquals(List.of("2", "+", "3"), tokens);
    }

    @Test
    void testWithSpaces() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer();
        List<String> tokens = tokenizer.tokenize("  12  *  ( 3 + 4 )  ");

        assertEquals(List.of("12", "*", "(", "3", "+", "4", ")"), tokens);
    }

    @Test
    void testInvalidSymbol() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer();
        assertThrows(RuntimeException.class, () -> tokenizer.tokenize("2a+3"));
    }
}

package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RpnEvaluatorTest {

    @Test
    void testSimpleEval() {
        RpnEvaluator evaluator = new RpnEvaluator();
        double result = evaluator.evaluate(List.of("2", "3", "+"));

        assertEquals(5, result);
    }

    @Test
    void testComplexEval() {
        RpnEvaluator evaluator = new RpnEvaluator();

        // Expression: ((8 + 5) * (6 - (7 + 5))) = (13 * -6) = -78
        List<String> rpn = List.of("8","5","+","6","7","5","+","-","*");

        assertEquals(-78, evaluator.evaluate(rpn));
    }

    @Test
    void testDivisionByZero() {
        RpnEvaluator evaluator = new RpnEvaluator();

        assertThrows(RuntimeException.class,
                () -> evaluator.evaluate(List.of("5", "0", "/")));
    }
}

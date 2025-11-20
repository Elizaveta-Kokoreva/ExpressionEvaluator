package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullCalculatorTest {

    @Test
    void testFullPipeline() {
        ExpressionTokenizer tokenizer = new ExpressionTokenizer();
        ParenthesesValidator brackets = new ParenthesesValidator();
        ExpressionSyntaxValidator syntax = new ExpressionSyntaxValidator();
        ExpressionRPNConverter rpn = new ExpressionRPNConverter();
        RpnEvaluator eval = new RpnEvaluator();

        String expr = "((8 + 5) * (6 - (7 + 5)))";

        List<String> tokens = tokenizer.tokenize(expr);
        brackets.validate(tokens);
        syntax.validate(tokens);

        List<String> rpnList = rpn.toRPN(tokens);
        double result = eval.evaluate(rpnList);

        assertEquals(-78, result);
    }
}

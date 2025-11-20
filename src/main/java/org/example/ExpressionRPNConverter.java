package org.example;

import java.util.*;

/**
 * Класс преобразует список токенов инфиксной записи
 * в обратную польскую нотацию (RPN).
 */
public class ExpressionRPNConverter {

    /**
     * Преобразует список токенов в формат RPN.
     *
     * @param tokens исходная последовательность токенов
     * @return список токенов в обратной польской записи
     */
    public List<String> toRPN(List<String> tokens) {

        List<String> output = new ArrayList<>();   // выходная последовательность
        Deque<String> stack = new ArrayDeque<>();  // стек операторов

        for (String token : tokens) {

            // 1. Числа сразу идут в выход
            if (isNumber(token)) {
                output.add(token);
            }

            // 2. Операторы
            else if (isOperator(token)) {
                while (!stack.isEmpty() &&
                        isOperator(stack.peek()) &&
                        precedence(stack.peek()) >= precedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }

            // 3. Открывающая скобка
            else if (token.equals("(")) {
                stack.push(token);
            }

            // 4. Закрывающая скобка — выталкиваем всё до "("
            else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop(); // удалить "("
            }
        }

        // 5. Переносим оставшиеся операторы в выход
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private boolean isNumber(String t) {
        return Character.isDigit(t.charAt(0));
    }

    private boolean isOperator(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    /**
     * Возвращает приоритет оператора.
     * * и / — приоритет 2
     * + и - — приоритет 1
     */
    private int precedence(String op) {
        if (op.equals("*") || op.equals("/")) return 2;
        return 1;
    }
}

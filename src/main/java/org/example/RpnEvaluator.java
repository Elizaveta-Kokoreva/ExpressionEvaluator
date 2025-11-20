package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Класс выполняет вычисление выражения,
 * представленного в обратной польской нотации (RPN).
 *
 * <p>Алгоритм использует стек чисел.
 * Числа помещаются в стек, операторы извлекают два числа,
 * выполняют операцию и возвращают результат обратно в стек.
 */
public class RpnEvaluator {

    /**
     * Вычисляет значение RPN-выражения.
     *
     * @param rpn список токенов в обратной польской записи
     * @return результат вычисления в виде числа double
     * @throws RuntimeException если выражение некорректно
     */
    public double evaluate(List<String> rpn) {

        Deque<Double> stack = new ArrayDeque<>();

        for (String token : rpn) {

            // Если число: кладём в стек
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Double.parseDouble(token));
            }

            // Если оператор: выполним операцию
            else {

                double b = stack.pop();
                double a = stack.pop();

                if (token.equals("+")) {
                    stack.push(a + b);
                }
                else if (token.equals("-")) {
                    stack.push(a - b);
                }
                else if (token.equals("*")) {
                    stack.push(a * b);
                }
                else if (token.equals("/")) {
                    if (b == 0) {
                        throw new RuntimeException("Ошибка: деление на 0");
                    }
                    stack.push(a / b);
                }
            }
        }

        return stack.pop();
    }

    /** Проверка, является ли токен числом */
    private boolean isNumber(String t) {
        return Character.isDigit(t.charAt(0));
    }

    /** Проверка, является ли токен оператором */
    private boolean isOperator(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    /** Применяет оператор к двум числам */
    private double applyOperator(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new RuntimeException("Деление на ноль");
                yield a / b;
            }
            default -> throw new RuntimeException("Неизвестный оператор: " + op);
        };
    }
}

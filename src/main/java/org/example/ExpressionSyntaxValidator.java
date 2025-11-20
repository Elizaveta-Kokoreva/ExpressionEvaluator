package org.example;

import java.util.List;

/**
 * Класс проверяет корректность последовательности токенов
 * в арифметическом выражении.
 * <p>На вход ожидается список токенов, полученный
 * из ExpressionTokenizer.</p>
        */
public class ExpressionSyntaxValidator {

    /**
     * Проверяет порядок токенов:
     * <ul>
     *     <li>число не может идти сразу после другого числа</li>
     *     <li>оператор не может идти сразу после другого оператора</li>
     *     <li>оператор не может быть в начале выражения</li>
     *     <li>оператор не может быть в конце выражения</li>
     *     <li>после '(' не может идти оператор</li>
     *     <li>перед ')' не может идти оператор</li>
     *     <li>пустые скобки "()" запрещены</li>
     * </ul>
     *
     * @param tokens список токенов выражения
     * @throws RuntimeException если токены расположены некорректно
     */
    public void validate(List<String> tokens) {

        if (tokens.isEmpty()) {
            throw new RuntimeException("Пустое выражение");
        }

        String prev = null;

        for (int i = 0; i < tokens.size(); i++) {
            String curr = tokens.get(i);

            // 1. Оператор в начале или конце выражения
            if (isOperator(curr)) {
                if (i == 0) {
                    throw new RuntimeException("Выражение не может начинаться с оператора: " + curr);
                }
                if (i == tokens.size() - 1) {
                    throw new RuntimeException("Оператор в конце выражения: " + curr);
                }
            }

            // 2. Два оператора подряд
            if (prev != null && isOperator(prev) && isOperator(curr)) {
                throw new RuntimeException("Два оператора подряд: " + prev + curr);
            }

            // 3. Число сразу за числом — ошибка (у нас нет поддержки слияния)
            if (prev != null && isNumber(prev) && isNumber(curr)) {
                throw new RuntimeException("Два числа подряд: " + prev + " " + curr);
            }

            // 4. "(" не может быть перед оператором
            if (prev != null && prev.equals("(") && isOperator(curr)) {
                throw new RuntimeException("После '(' не может идти оператор: " + curr);
            }

            // 5. ")" не может быть после оператора
            if (prev != null && isOperator(prev) && curr.equals(")")) {
                throw new RuntimeException("Перед ')' не должен стоять оператор: " + prev);
            }

            // 6. Пустые скобки "()"
            if (prev != null && prev.equals("(") && curr.equals(")")) {
                throw new RuntimeException("Пустые скобки запрещены");
            }

            prev = curr;
        }
    }

    /**
     * Проверяет является ли токен оператором
     */
    private boolean isOperator(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    /**
     * Проверяет является ли токен числом.
     */
    private boolean isNumber(String t) {
        return Character.isDigit(t.charAt(0));
    }
}

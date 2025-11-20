package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс предназначен для разбиения строки арифметического выражения
 * на отдельные токены: числа, операторы и скобки.
 *
 * <p>Поддерживаются только:
 * <ul>
 *     <li>целые числа</li>
 *     <li>операции +, -, *, /</li>
 *     <li>скобки ( и )</li>
 * </ul>
 */
public class ExpressionTokenizer {

    /**
     * Каждое число и каждый оператор
     * становятся отдельными элементами списка.
     *
     * @param expr строка с выражением. Пробелы допускаются.
     * @return список токенов в порядке следования
     * @throws RuntimeException если встречается недопустимый символ
     */
    public List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // 1. Пропускаем пробелы
            if (Character.isWhitespace(c)) {
                continue;
            }

            // 2. Если символ — цифра, накапливаем его в буфер числа
            if (Character.isDigit(c)) {
                buffer.append(c);
            }
            else {
                // 3. Число закончено — добавляем его в список токенов
                if (!buffer.isEmpty()) {
                    tokens.add(buffer.toString());
                    buffer.setLength(0);
                }

                // 4. Операторы и скобки — отдельные токены
                if (isOperator(c) || isParenthesis(c)) {
                    tokens.add(String.valueOf(c));
                } else {
                    // 5. Если символ не оператор/цифра/скобка — выражение некорректно
                    throw new RuntimeException("Недопустимый символ: " + c);
                }
            }
        }

        // 6. Добавляем последнее число (если оно было)
        if (!buffer.isEmpty()) {
            tokens.add(buffer.toString());
        }

        return tokens;
    }

    /**
     * Проверяет, является ли символ арифметическим оператором.
     *
     * @param c символ для проверки
     * @return true, если символ — +, -, *, /
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Проверяет, является ли символ скобкой.
     *
     * @param c символ для проверки
     * @return true, если символ — '(' или ')'
     */
    private boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }
}

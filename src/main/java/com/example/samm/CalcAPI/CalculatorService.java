package com.example.samm.CalcAPI;

import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class CalculatorService {


    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean hasHigherOrEqualPriority(char op1, char op2) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    private static void applyOperation(Stack<Double> numbers, char operator) {
        double num2 = numbers.pop();
        double num1 = numbers.pop();

        switch (operator) {
            case '+':
                numbers.push(num1 + num2);
                break;
            case '-':
                numbers.push(num1 - num2);
                break;
            case '*':
                numbers.push(num1 * num2);
                break;
            case '/':
                numbers.push(num1 / num2);
                break;
        }
    }

    public String calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(numBuilder.toString()));
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && hasHigherOrEqualPriority(operators.peek(), ch)) {
                    applyOperation(numbers, operators.pop());
                }
                operators.push(ch);
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperation(numbers, operators.pop());
                }
                operators.pop(); // '('
            }
        }

        while (!operators.isEmpty()) {
            applyOperation(numbers, operators.pop());
        }

        return String.valueOf(numbers.pop());
    }
}

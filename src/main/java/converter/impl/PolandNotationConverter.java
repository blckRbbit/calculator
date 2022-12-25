package converter.impl;

import converter.ExpressionConverter;
import exception.UnexpectedTokenException;
import math.action.Token;
import math.action.impl.NumberToken;
import util.Util;
import validator.ArithmeticExpressionValidator;

import java.util.Set;
import java.util.Stack;

import static init.TokenInitializer.getAppTokens;

public class PolandNotationConverter implements ExpressionConverter {
    private final String SEPARATOR;
    private final Set<Token> TOKENS;

    public PolandNotationConverter() {
        this.SEPARATOR = Util.getAppSeparator();
        this.TOKENS = getAppTokens();
    }

    @Override
    public Stack<Token> convertTo(String expression) throws NullPointerException{
        expression = expression.trim();
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator(expression);
        Stack<Token> result = new Stack<>();
        if (validator.validate()) {
            expression = replaceExpression(expression);
            String[] symbols = expression.split(SEPARATOR);
            Token token;
            for (String symbol : symbols) {
                if (isNumeric(symbol)) {
                    token = new NumberToken(symbol);
                } else {
                    if (symbol.equals(SEPARATOR)) continue;
                    token = getToken(symbol);
                }
                result.push(token);
            }
        } else {
            return null;
        }
        return result;
    }

    private String replaceExpression(String expression) {
        expression = prepare(expression);
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);
            priority = getPriority(character);

            if (priority == 0) current.append(character);
            if (priority == 1) stack.push(character);

            if (priority > 1) {
                current.append(SEPARATOR);
                while (!stack.isEmpty()) {
                    if (getPriority(stack.peek()) >= priority) current.append(stack.pop()).append(SEPARATOR);
                    else break;
                }
                stack.push(character);
            }

            if (priority == -1) {
                current.append(SEPARATOR);
                while (getPriority(stack.peek()) != 1) {
                    current.append(stack.pop());
                }
                stack.pop();
            }
            
        }
        current.append(SEPARATOR);
        while (!stack.isEmpty()) current.append(stack.pop()).append(SEPARATOR);
        return current.toString();
    }

    private String prepare(String expression) {
        expression = expression.replaceAll(SEPARATOR, "").replace(",", ".");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == '-') {
                if (i == 0) {
                    result.append("0");
                }

                else if (expression.charAt(i - 1) == '(') {
                    result.append("0");
                }
            }
            result.append(symbol);
        }
        return result.toString();
    }

    private boolean isNumeric(String symbol) {
        try {
            Double.parseDouble(symbol);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private  int getPriority(Character character) {
        String symbol = String.valueOf(character);
        return getToken(symbol).getPriority();
    }

    private Token getToken(String symbol) {
        if (symbol.equals(SEPARATOR)) {
            symbol = "<Space>";
        }
        if (symbol.equals("")) {
            symbol = "<Empty line>";
        }
        Token result = null;
        for (Token t : TOKENS) {
            if (t.getSymbol().equals(symbol)) {
                result = t;
                break;
            }
        }
        if (result == null) throw new UnexpectedTokenException("Invalid character in expression: " + symbol);
        return result;
    }

    /**Геттеры для тестирования**/

    public String replaceExpressionForTest(String testExpression) {
        return replaceExpression(testExpression);
    }

    public String prepareForTest(String testExpression) {
        return prepare(testExpression);
    }

    public boolean isNumericForTest(String testSymbol) {
        return isNumeric(testSymbol);
    }

    public int getPriorityForTest(Character testCharacter) {
        return getPriority(testCharacter);
    }

    public Token getTokenForTest(String testSymbol) {
        return getToken(testSymbol);
    }

}

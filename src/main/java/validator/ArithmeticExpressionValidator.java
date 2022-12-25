package validator;

import exception.EmptyExpressionException;
import exception.UnexpectedCharacterException;
import math.action.Token;

import java.util.HashSet;
import java.util.Set;

import static init.TokenInitializer.getAppTokens;

public class ArithmeticExpressionValidator implements Validator {
    private String expression;
    private final Set<String> operators = new HashSet<>();

    public ArithmeticExpressionValidator(String expression) {
        this.expression = expression.replace(" ", "").replace(",", ".");
        Set<Token> tokens = getAppTokens();
        for (Token token : tokens) {
            String symbol = token.getSymbol();
            if (!symbol.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) operators.add(symbol);
        }
    }

    @Override
    public boolean validate() {
        return isContainsValidSymbols();
    }

    @Override
    public boolean isOperator(String in) {
        return operators.contains(in);
    }

    private boolean isContainsValidSymbols() {
        if (isBracketsCountCorrect()) {
            if (expressionEndIsCorrect()) {
                StringBuilder builder = new StringBuilder();
                for (String operator : operators) {
                    builder.append(operator);
                }
                String regex = String.format("[.0123456789%s]+", builder);
                return expression.matches(regex);
            }
            throw new RuntimeException("An unexpected error has occurred.");
        }
        throw new RuntimeException("Wrong number of open or closed brackets.");
    }

    private boolean isBracketsCountCorrect() {
        int openBracketsCount = 0;
        int closeBracketsCount = 0;
        for (String c : expression.split("")) {
            if (c.equals("(")) {
                openBracketsCount += 1;
            }
            if (c.equals(")")) {
                closeBracketsCount += 1;
            }
        }
        return openBracketsCount == closeBracketsCount;
    }

    private boolean expressionEndIsCorrect() {
        if (expressionBeginIsCorrect()) {
            String endSymbol = String.valueOf(expression.charAt(expression.length() - 1));
            return endSymbol.equals(")") || isDigit(endSymbol) || endSymbol.equalsIgnoreCase("q");
        }
        return expressionBeginIsCorrect();
    }

    private boolean expressionBeginIsCorrect() {
        String temp;
        String begin;
        expression = trimCorrectSymbolsInExpressionBegin();
        if (isQuit()) {
            return true;
        }
        if (expressionBeginIsMinus()) {
            temp = expression.substring(1);
            if (temp.charAt(0) == '(') {
                begin = temp.charAt(1) == '-' ? temp.substring(2, 3) : temp.substring(1, 2);
            } else {
                begin = temp.substring(0, 1);
            }
            return isDigit(begin);
        }

        temp = expressionBeginIsBracket() ? expression.substring(1) : expression;
        if (temp.length() > 1) {
            begin = temp.charAt(0) == '-' ? temp.substring(0, 2) : temp.substring(0, 1);
            return isDigit(begin);
        } else {
            return isDigit(temp);
        }
    }

    private boolean isQuit() {
        return (expression.startsWith("q") || expression.startsWith("Q"));
    }

    private String trimCorrectSymbolsInExpressionBegin() {
        int j = 0;
        String[] arr = expression.split("");
        for (String s : arr) {
            if (s.equals("(") || s.equals("-")) {
                j++;
            } else break;
        }
        return expression.substring(j);
    }

    private boolean expressionBeginIsBracket() {
        if (expressionIsNotEmpty()) {
            String begin = expression.split("")[0];
            return begin.equals("(");
        }
        return expressionIsNotEmpty();
    }

    private boolean expressionBeginIsMinus() {
        if (expressionIsNotEmpty()) {
            String begin = expression.split("")[0];
            return begin.equals("-");
        }
        return expressionIsNotEmpty();
    }

    @Override
    public boolean isDigit(String input) {
        String startSymbol = String.valueOf(expression.charAt(0));
        if (expressionIsNotEmpty()) {
            if (startSymbol.equalsIgnoreCase("q")) {
                return expressionIsNotEmpty();
            }
            String regex = "^-*(?=[^.])\\d*\\.?((?=[^.])\\d*)$";
            if (!input.matches(regex)) {
                throw new UnexpectedCharacterException("Invalid character in arithmetic expression.");
            }
            return input.matches(regex);
        }
        return expressionIsNotEmpty();
    }

    private boolean expressionIsNotEmpty() {
        if (this.expression.isEmpty()) {
            throw new EmptyExpressionException("Expression must not be empty.");
        }
        return true;
    }

    /**
     * Геттеры для модульного тестирования (сделано для того, чтобы отобразить разработку по TDD).
     */

    public boolean isContainsValidSymbolsForTest() {
        return isContainsValidSymbols();
    }

    public boolean expressionEndIsCorrectForTest() {
        return expressionEndIsCorrect();
    }

    public boolean expressionBeginIsCorrectForTest() {
        return expressionBeginIsCorrect();
    }

    public boolean expressionBeginIsBracketForTest() {
        return expressionBeginIsBracket();
    }

    public boolean expressionBeginIsMinusForTest() {
        return expressionBeginIsMinus();
    }

    public boolean expressionIsNotEmptyForTest() {
        return expressionIsNotEmpty();
    }
}

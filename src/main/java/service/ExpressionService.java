package service;

import converter.ExpressionConverter;
import converter.impl.PolandNotationConverter;
import math.action.MathAction;
import math.action.Token;
import math.action.impl.QuitToken;

import java.util.Stack;

public class ExpressionService {

    private final ExpressionConverter converter;

    public ExpressionService() {
        this.converter = new PolandNotationConverter();
    }

    public String run(String expression) {
        return String.format("Task: %s; Answer: %s;%n", expression, calculate(expression));
    }

    private String calculate(String expression) {
        Stack<Token> tokens = converter.convertTo(expression);
        if (tokens==null) {
            return "The expression you entered may contain an error.";
        }
        Stack<Double> result = new Stack<>();

        for (Token token : tokens) {
            if (token instanceof QuitToken) {
                result.push(-0.);
                break;
            }
            if (token.isNumber()) {
                result.push(Double.parseDouble(token.getSymbol()));
            } else {
                MathAction action = (MathAction) token;
                double answer;
                Double a = result.pop();
                Double b = result.pop();
                if (b == null) {
                    answer = action.calculate(a);
                } else {
                    answer = action.calculate(b, a);
                }
                result.push(answer);
            }
        }

        return String.valueOf(result.pop());
    }

    /**Геттеры для тестирования**/

    public String calculateForTest(String testExpression) {
        return calculate(testExpression);
    }
}

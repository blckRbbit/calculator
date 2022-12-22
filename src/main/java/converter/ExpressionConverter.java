package converter;

import math.action.Token;

import java.util.Stack;

public interface ExpressionConverter {
   Stack<Token> convertTo(String expression);
}

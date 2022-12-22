package validator;

import exception.EmptyExpressionException;
import exception.UnexpectedCharacterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticExpressionValidatorTest {

    @Test
    void expressionIsNotEmpty() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("+-");
        assertTrue(validator.expressionIsNotEmptyForTest());
    }

    @Test
    void expressionIsEmpty() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("   ");
        assertThrows(EmptyExpressionException.class, validator::expressionIsNotEmptyForTest);
    }

    @Test
    void isDigit() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("some");
        assertTrue(validator.isDigit("1.1"));
        assertTrue(validator.isDigit("-0.1"));
        assertTrue(validator.isDigit("0.1"));
        assertTrue(validator.isDigit("0"));
        assertThrows(UnexpectedCharacterException.class, () -> validator.isDigit("q"));
    }

    @Test
    void isNotDigit() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("some");
        assertThrows(UnexpectedCharacterException.class, () -> validator.isDigit("-.1"));
        assertThrows(UnexpectedCharacterException.class, () -> validator.isDigit("1.1.1"));
        assertThrows(UnexpectedCharacterException.class, () -> validator.isDigit("1q"));
        assertThrows(UnexpectedCharacterException.class, () -> validator.isDigit("q"));
    }

    @Test
    void expressionBeginIsMinus() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("-");
        assertTrue(validator.expressionBeginIsMinusForTest());
    }

    @Test
    void expressionBeginIsNotMinus() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator(")");
        assertFalse(validator.expressionBeginIsMinusForTest());
    }

    @Test
    void expressionBeginIsBracket() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(");
        assertTrue(validator.expressionBeginIsBracketForTest());
    }

    @Test
    void expressionBeginIsNotBracket() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator(")");
        assertFalse(validator.expressionBeginIsBracketForTest());
    }

    @Test
    void expressionBeginIsCorrect() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(-1");
        ArithmeticExpressionValidator validator1 = new ArithmeticExpressionValidator("-1");
        ArithmeticExpressionValidator validator2 = new ArithmeticExpressionValidator("0.1");
        ArithmeticExpressionValidator validator3 = new ArithmeticExpressionValidator("-0");
        ArithmeticExpressionValidator validator4 = new ArithmeticExpressionValidator("q");

        assertTrue(validator.expressionBeginIsCorrectForTest());
        assertTrue(validator1.expressionBeginIsCorrectForTest());
        assertTrue(validator2.expressionBeginIsCorrectForTest());
        assertTrue(validator3.expressionBeginIsCorrectForTest());
        assertTrue(validator4.expressionBeginIsCorrectForTest());
    }

    @Test
    void expressionBeginIsNotCorrect() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator(")-1");
        ArithmeticExpressionValidator validator1 = new ArithmeticExpressionValidator("a");
        ArithmeticExpressionValidator validator2 = new ArithmeticExpressionValidator("*0");
        ArithmeticExpressionValidator validator3 = new ArithmeticExpressionValidator("-+");

        assertThrows(UnexpectedCharacterException.class, validator::expressionBeginIsCorrectForTest);
        assertThrows(UnexpectedCharacterException.class, validator1::expressionBeginIsCorrectForTest);
        assertThrows(UnexpectedCharacterException.class, validator2::expressionBeginIsCorrectForTest);
        assertThrows(UnexpectedCharacterException.class, validator3::expressionBeginIsCorrectForTest);
    }

    @Test
    void expressionEndIsCorrect() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("1)");
        ArithmeticExpressionValidator validator2 = new ArithmeticExpressionValidator("q");
        assertTrue(validator.expressionEndIsCorrectForTest());
        assertTrue(validator2.expressionEndIsCorrectForTest());
    }

    @Test
    void expressionEndIsNotCorrect() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator(".(");
        assertThrows(UnexpectedCharacterException.class, validator::expressionEndIsCorrectForTest);
    }

    @Test
    void isContainsValidSymbols() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(12345670)+-*/89");
        assertTrue(validator.isContainsValidSymbolsForTest());
    }

    @Test
    void isContainsNotValidSymbolsWhenOrderSymbolsIsWrong() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator(")12345670)+-*/89");
        assertThrows(RuntimeException.class, validator::isContainsValidSymbolsForTest);
    }

    @Test
    void isContainsNotValidSymbolsWhenUnexpectedSymbol() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(12345670O)+-*/89");
        assertFalse(validator.isContainsValidSymbolsForTest());
    }

    @Test
    void isOperand() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(12345670O)+-*/89");
        assertTrue(validator.isOperator("("));
        assertTrue(validator.isOperator(")"));
        assertTrue(validator.isOperator("*"));
        assertTrue(validator.isOperator("/"));
        assertTrue(validator.isOperator("+"));
        assertTrue(validator.isOperator("-"));
    }

    @Test
    void isNotOperand() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(12345670O)+-*/89");
        assertFalse(validator.isOperator(","));
        assertFalse(validator.isOperator("1"));
        assertFalse(validator.isOperator("1.1"));
        assertFalse(validator.isOperator("-0.5"));
        assertFalse(validator.isOperator("a"));
    }

    @Test
    void validate() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(12345670)+-*/89");
        assertTrue(validator.validate());
    }

    @Test
    void validateWhenExpressedSymbol() {
        ArithmeticExpressionValidator validator = new ArithmeticExpressionValidator("(12345670O)+-*/89");
        assertThrows(UnexpectedCharacterException.class, validator::validate);
    }
}

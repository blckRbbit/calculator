package converter;

import converter.impl.PolandNotationConverter;
import exception.UnexpectedTokenException;
import exception.ValidateException;
import math.action.Token;
import math.action.impl.DotToken;
import math.action.impl.NumberToken;
import math.action.impl.SubtractionToken;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class PolandNotationConverterTest {

    //todo переписать, чтобы каждый метод выполнял только одно сравнение.

    @Test
    void getToken() {
        PolandNotationConverter converter = new PolandNotationConverter();

        assertEquals(converter.getTokenForTest("0"), new NumberToken());
        assertEquals(converter.getTokenForTest("9"), new NumberToken("9"));
        assertEquals(converter.getTokenForTest("."), new DotToken());
        assertThrows(UnexpectedTokenException.class, () -> converter.getTokenForTest(","));
    }

    @Test
    void getPriority() {
        PolandNotationConverter converter = new PolandNotationConverter();

        assertEquals(converter.getPriorityForTest('7'), 0);
        assertEquals(converter.getPriorityForTest('0'), 0);
        assertEquals(converter.getPriorityForTest('+'), 2);
        assertEquals(converter.getPriorityForTest('-'), 2);
        assertEquals(converter.getPriorityForTest('*'), 3);
        assertEquals(converter.getPriorityForTest('/'), 3);
        assertEquals(converter.getPriorityForTest('('), 1);
        assertEquals(converter.getPriorityForTest(')'), -1);
    }

    @Test
    void isNumeric() {
        PolandNotationConverter converter = new PolandNotationConverter();

        assertTrue(converter.isNumericForTest("-8.9"));
        assertFalse(converter.isNumericForTest("-9,3"));
    }

    @Test
    void prepare() {
        PolandNotationConverter converter = new PolandNotationConverter();

        assertEquals(converter.prepareForTest("-(-2 +  2) * 2"), "0-(0-2+2)*2");
    }

    @Test
    void replaceExpression() {
        PolandNotationConverter converter = new PolandNotationConverter();

        assertEquals(converter.replaceExpressionForTest("(2 + 2) *   2"), "2 2 + 2 * ");
        assertEquals(converter.replaceExpressionForTest("2 + 2 *   2"), "2 2 2 * + ");
    }

    @Test
    void convertTo() {
        PolandNotationConverter converter = new PolandNotationConverter();

        Stack<Token> example = getTestedTokens();
        Stack<Token> tokens = converter.convertTo("2  -2");
        Stack<Token> notEquals = converter.convertTo("2 + 2");

        assertEquals(example, tokens);
        assertEquals(example.peek(), tokens.peek());
        assertEquals(example.pop(), tokens.pop());

        assertNotEquals(example, notEquals);

        assertThrows(RuntimeException.class, () -> converter.convertTo(") 2 + 2 )"));

    }

    private Stack<Token> getTestedTokens() {
        Stack<Token> result = new Stack<>();
        result.push(new NumberToken("2"));
        result.push(new NumberToken("2"));
        result.push(new SubtractionToken());
        return result;
    }
}

package action;

import math.action.Token;
import math.action.MathAction;
import math.action.impl.MultiplyToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyTokenTest {
    @Test
    void create() {
        Token token = new MultiplyToken();
        assertEquals(token.getPriority(), 3);
        assertEquals(token.getSymbol(), "*");
    }

    @Test
    void calculate() {
        MathAction action = new MultiplyToken();
        assertEquals(action.calculate(3, 2), 6);
    }
}

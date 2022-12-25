package action;

import math.action.Token;
import math.action.MathAction;
import math.action.impl.DivideToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivideTokenTest {
    @Test
    void create() {
        Token token = new DivideToken();
        assertEquals(token.getPriority(), 3);
        assertEquals(token.getSymbol(), "/");
    }

    @Test
    void calculate() {
        MathAction action = new DivideToken();
        assertEquals(action.calculate(3, 2), 1.5);
    }
}

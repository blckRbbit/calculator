package action;

import math.action.MathAction;
import math.action.Token;
import math.action.impl.SubtractionToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionTokenTest {
    @Test
    void create() {
        Token token = new SubtractionToken();
        assertEquals(token.getPriority(), 2);
        assertEquals(token.getSymbol(), "-");
    }

    @Test
    void calculate() {
        MathAction action = new SubtractionToken();
        assertEquals(action.calculate(3, 2), 1);
        assertEquals(action.calculate(-3, -2), -1);
    }
}

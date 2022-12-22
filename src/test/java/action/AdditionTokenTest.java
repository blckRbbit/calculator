package action;

import math.action.Token;
import math.action.MathAction;
import math.action.impl.AdditionToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionTokenTest {
    @Test
    void create() {
        Token token = new AdditionToken();
        assertEquals(token.getPriority(), 2);
        assertEquals(token.getSymbol(), "+");
    }

    @Test
    void calculate() {
        MathAction action = new AdditionToken();
        assertEquals(action.calculate(3, 2), 5);
    }
}

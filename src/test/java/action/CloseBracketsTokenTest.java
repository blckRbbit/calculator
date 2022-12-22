package action;

import math.action.Token;
import math.action.impl.CloseBracketsToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CloseBracketsTokenTest {
    @Test
    void create() {
        Token token = new CloseBracketsToken();
        assertEquals(token.getPriority(), -1);
        assertEquals(token.getSymbol(), ")");
    }
}

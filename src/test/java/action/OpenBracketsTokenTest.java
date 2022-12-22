package action;

import math.action.Token;
import math.action.impl.OpenBracketsToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenBracketsTokenTest {
    @Test
    void create() {
        Token token = new OpenBracketsToken();
        assertEquals(token.getPriority(), 1);
        assertEquals(token.getSymbol(), "(");
    }
}

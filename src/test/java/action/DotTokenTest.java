package action;

import math.action.Token;
import math.action.impl.DotToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DotTokenTest {
    @Test
    void create() {
        Token token = new DotToken();
        assertEquals(token.getPriority(), 0);
        assertEquals(token.getSymbol(), ".");
    }
}

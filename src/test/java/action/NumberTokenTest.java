package action;

import math.action.Token;
import math.action.impl.NumberToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTokenTest {
    @Test
    void create() {
        Token token = new NumberToken("1");
        assertEquals(token.getPriority(), 0);
        assertEquals(token.getSymbol(), "1");

        Token nullNumberToken = new NumberToken();
        assertEquals(nullNumberToken.getPriority(), 0);
        assertEquals(nullNumberToken.getSymbol(), "0");
    }
}

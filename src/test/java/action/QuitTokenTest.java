package action;

import math.action.Token;
import math.action.impl.QuitToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuitTokenTest {
    @Test
    void create() {
        Token token = new QuitToken();
        assertEquals(token.getPriority(), 0);
        assertEquals(token.getSymbol(), "q");
    }
}

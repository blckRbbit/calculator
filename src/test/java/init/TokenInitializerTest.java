package init;

import math.action.Token;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import static init.TokenInitializer.getAppTokens;

public class TokenInitializerTest {
    @Test
    void init() {
        Set<Token> test = getAppTokens();
        assertEquals(18, test.size());
    }
}

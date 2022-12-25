package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionServiceTest {
    @Test
    void calculate() {
        ExpressionService service = new ExpressionService();
        assertEquals("6.0", service.calculateForTest("2 + 2 * 2"));
        assertEquals("8.0", service.calculateForTest("(2 + 2) * 2"));
        assertEquals("-2.0", service.calculateForTest("-2"));
        assertEquals("-5.0", service.calculateForTest("-3 - 2"));
        assertEquals("5.0", service.calculateForTest("-(-3 - 2)"));
    }

}

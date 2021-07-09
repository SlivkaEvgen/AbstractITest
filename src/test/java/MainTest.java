import myMiniShop.BasketImpl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    BasketImpl basket = new BasketImpl();

    @Test
    public void testMain() {
        basket.calculateTotalCost("ABCDABA");
        assertEquals(13.25d, basket.getTotalPrice(), 0.01d);

        basket.setTotalPrice(15);
        assertEquals(15.00d, basket.getTotalPrice(), 0.01d);

        basket.calculateTotalCost("\"\\\\.(gif|jpg|png)$\"");
        assertEquals(0.0d, basket.getTotalPrice(), 0.01d);

        basket.calculateTotalCost(null);
        assertEquals(0.0d, basket.getTotalPrice(), 0.01d);

        basket.calculateTotalCost("12345//)'.");
        assertEquals(0.0d, basket.getTotalPrice(), 0.01d);

        basket.setBasket("aaa, aaa, aaa");
        assertEquals("aaa, aaa, aaa", basket.getBasket());
    }

    @Test(expected = AssertionError.class)
    public void shouldIgnoreIndexOFOrNull() {
        String null1 = "ABCDABA";
        try {
            basket.setBasket(null1);
            basket.setTotalPrice(0);
            fail("Not");
        } catch (AssertionError es) {
            assertEquals("ABCDABA", basket.getBasket());
            assertEquals(12.12d, basket.getTotalPrice(), 0.01d);

            basket.setBasket(null);
            assertEquals(12.0d, basket.getTotalPrice(), 0.01d);
            assertNull(null1);
        }
    }
}
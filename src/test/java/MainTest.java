import myMiniShop.Basket;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    @Test
    public void testMain() {
        Assert.assertEquals(13.25d, new Basket("ABCDABA").getTotalPrice(), 0.01d);
        Assert.assertEquals(0.0d, new Basket("\"\\\\.(gif|jpg|png)$\"").getTotalPrice(), 0.01d);
        Assert.assertEquals(0.0d, new Basket(null).getTotalPrice(), 0.01d);

        new Basket("12345//)'.").countProductsInBasket("12345//)'.");
        Assert.assertEquals(0.0d, new Basket("12345//)'.").getTotalPrice(), 0.01d);

        new Basket("ABCDABA").setTotalPrice(15);
        Assert.assertEquals(13.25d, new Basket("ABCDABA").getTotalPrice(), 0.01d);
        Assert.assertEquals(new Basket("AAA, AAA, AAA"), new Basket("aaa, aaa, aaa"));
    }

    @Test(expected = AssertionError.class)
    public void shouldIgnoreIndexOFOrNull() {
        String null1 = "ABCDABA";
        try {
            new Basket("ABCDABA").countProductsInBasket(null);
            Assert.fail("Not");
        } catch (AssertionError es) {
            Assert.assertEquals("ABCDABA", new Basket("ABCDABA").getBasket());
            Assert.assertEquals(12.12d, new Basket("ABCDABA").getTotalPrice(), 0.01d);
            Assert.assertEquals(new Basket("ABCDABA"), new Basket(null));
            Assert.assertNull(null1);
        }
    }
}
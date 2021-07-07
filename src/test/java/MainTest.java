import myMiniShop.Basket;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    String basket = "ABCDABA";
    String basket3 = "12345//)'.";
    String basket4 = "\"\\\\.(gif|jpg|png)$\"";
    String basketNull = null;
    @Test
    public void testMain() {
        Basket basket = new Basket(this.basket);
        double expected1 = 13.25d;
        Assert.assertEquals(expected1, basket.getTotalPrice(), 0.01d);

        Basket basket4 = new Basket(this.basket4);
        double expected4 = 0.0d;
        Assert.assertEquals(expected4, basket4.getTotalPrice(), 0.01d);

        Basket basketNull = new Basket(this.basketNull);
        double expectedNull = 0.0d;
        Assert.assertEquals(expectedNull, basketNull.getTotalPrice(), 0.01d);

        Basket basket1 = new Basket(basket3);
        basket1.countProductsInBasket(basket3);
        double expected3 = 0.0d;
        Assert.assertEquals(expected3, basket1.getTotalPrice(), 0.01d);

        basket.setTotalPrice(15);
        double expected2 = 15.00d;
        Assert.assertEquals(expected2, basket.getTotalPrice(), 0.01d);

        Basket result1 = new Basket("AAA, AAA, AAA");
        Assert.assertEquals(result1, new Basket("aaa, aaa, aaa"));

    }
    @Test(expected = AssertionError.class)
    public void should_Ignore_IndexOF_Or_Null(){
        Basket basket = new Basket(this.basket);
       // Product d = new Product("A",2.25d,17.0d, 2.0d, 17.0d);
        try {
            basket.countProductsInBasket(null);
            Assert.fail("Not");
        }catch (AssertionError es) {
            Assert.assertEquals(this.basket, basket.getBasket());
            Assert.assertEquals(12.12d, basket.getTotalPrice(),0.01d);
            Assert.assertEquals(basket, new Basket(null));
            Assert.assertNull(this.basket);
        }

    }
}
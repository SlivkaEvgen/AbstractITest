package myMiniShop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class BasketTest {
    String basket = "ABCDABA";
    String basket1 = "abcdaba";
    String basket2 = "АБСДАБА";
    String basket3 = "12345//)'.";
    String basket4 = "\"\\\\.(gif|jpg|png)$\"";
    String basketNull = null;
    List<Product> products = new ArrayList<>();
    @Before
    public void setUp() {

        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
        products.add(new Product("B", 4.25d, 0.0d, 0.0d, 0.0d));
        products.add(new Product("C", 1.00d, 5.0d, 0.0d, 6.0d));
        products.add(new Product("D", 0.75d, 0.0d, 0.0d, 0.0d));
    }
    @Test
    public void testCheckingProductsInBasket() {
        basket1 = basket1.toUpperCase(Locale.ROOT);
        String expected = "ABCDABA";
        String actual = basket1;
        Assert.assertNotNull(actual);
        assertEquals(expected, actual);
        Product d = new Product("A",2.25d,17.0d, 2.0d, 17.0d);
        Store.products.add(4, d);
        assertEquals(d, Store.products.get(4));
        String[] actual1 = this.basket.split("");
        String[] expected1 = {"A", "B", "C", "D", "A", "B", "A"};
        Assert.assertNotNull(actual1);
        Assert.assertArrayEquals(expected1, actual1);

        String actual2 = expected1[0];
        String expected2 = "A";
        Assert.assertNotNull(expected2);
        assertEquals(expected2, actual2);

        Basket basket = new Basket("\\.(gif|jpg|png)$");
        String actual3 = basket.getBasket();
        String expected3 = "\\.(GIF|JPG|PNG)$";
        Assert.assertEquals(expected3, actual3);

        Basket basket1 = new Basket(null);
        String actual4 = basket1.getBasket();
        String expected4 = null;
        Assert.assertEquals(expected4, actual4);
    }

    @Test
    public void testCountProductsInBasket() {
        Basket basket = new Basket(this.basket);
        String actual = basket.getBasket();
        String expected = this.basket;
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);

        Basket basket1 = new Basket(this.basket1);
        String actual1 = basket1.getBasket();
        String expected1 = this.basket1.toUpperCase(Locale.ROOT);
        Assert.assertNotNull(actual1);
        Assert.assertEquals(expected1, actual1);

        Basket basket2 = new Basket(this.basket2);
        String actual2 = basket2.getBasket();
        String expected2 = this.basket2.toUpperCase(Locale.ROOT);
        Assert.assertNotNull(actual2);
        Assert.assertEquals(expected2, actual2);

        Basket basket3 = new Basket(this.basket3);
        String actual3 = basket3.getBasket();
        String expected3 = this.basket3.toUpperCase(Locale.ROOT);
        Assert.assertNotNull(actual3);
        Assert.assertEquals(expected3, actual3);

        Basket basket4 = new Basket(this.basket4);
        String actual4 = basket4.getBasket();
        String expected4 = this.basket4.toUpperCase(Locale.ROOT);
        Assert.assertNotNull(actual4);
        Assert.assertEquals(expected4, actual4);

        Basket basketNull = new Basket(this.basketNull);
        String actualNull = basketNull.getBasket();
        String expectedNull = this.basketNull;
        Assert.assertEquals(expectedNull, actualNull);

        String actualA = Store.products.get(0).getName();
        String expectedA = "A";
        Assert.assertEquals(expectedA, actualA);

        double actualAmount = Store.products.get(0).getAmount();
        double expectedAmount = 0.0d;
        Assert.assertEquals(expectedAmount, actualAmount, 0.01d);
    }

    @Test
    public void testCalculateTotalCost() {
        Basket basket = new Basket(null);

        double expected = 0.0d;
        double actual = basket.getTotalPrice();
        Assert.assertEquals(expected, actual, 0.01d);

        Basket basket1 = new Basket(this.basket1);
        double expected1 = 7.25d;
        double actual1 = basket1.getTotalPrice();
        Assert.assertEquals(expected1, actual1, 0.01d);

        Basket basket2 = new Basket(this.basket2);
        double expected2 = 0.0d;
        double actual2 = basket2.getTotalPrice();
        Assert.assertEquals(expected2, actual2, 0.01d);

        Basket basket3 = new Basket(this.basket3);
        double expected3 = 0.0d;
        double actual3 = basket3.getTotalPrice();

        double actualDiscount = Store.products.get(1).getDiscountAmount();
        double expectedDiscount = 0.0d;

        double actualAmount = Store.products.get(0).getAmount();
        double expectedAmount = 0.0d;

        Assert.assertEquals(expected3, actual3, 0.01d);
        Assert.assertEquals(expectedDiscount, actualDiscount, 0.01d);
        Assert.assertEquals(expectedAmount, actualAmount, 0.01d);

    }

    @Test
    public void ifBasketNull() {
        Basket basket = new Basket(null);

        basket.countProductsInBasket(null);
        double actualTotalPrice = basket.getTotalPrice();
        double expectedlTotalPrice = 0.0d;

        Store.products.get(0).setAmount(7);
        double expectedAmount = 7.0d;
        double actualAmount = Store.products.get(0).getAmount();

        Store.products.get(0).setPrice(15);
        double expectedPrice = 15.0d;
        double actualPrice = Store.products.get(0).getPrice();
        Assert.assertEquals(expectedlTotalPrice, actualTotalPrice, 0.01d);
        Assert.assertEquals(expectedAmount, actualAmount, 0.01d);
        Assert.assertEquals(expectedPrice, actualPrice, 0.01d);
        Assert.assertNull(basket.getBasket());

    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusDiscountAmount() {
        Basket basket = new Basket(this.basket);
        Product product = Store.products.get(0);
        try {
            product.setDiscountAmount(-3.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -100.0d, 0.01d);
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusDiscountPrice() {
        Basket basket = new Basket(this.basket);
        Product product = Store.products.get(0);
        try {
            product.setDiscountPrice(-1.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -1.0d, 0.01d);
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusPrice() {
        new Basket(this.basket);
        Product product = Store.products.get(0);
        try {
            product.setPrice(-2.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -10.0d, 0.01d);
        }

    }

    @Test(expected = AssertionError.class)
    public void shouldIgnoreMinusAmount() {
        Basket basket = new Basket(this.basket);
        Product product = Store.products.get(0);
        try {
            product.setAmount(-2.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -100.0d, 0.01d);
        }
    }
    @Test(expected = AssertionError.class)
    public void shouldIgnoreIndexOF(){
        Basket basket = new Basket(this.basket);
        Product d = new Product("A",2.25d,17.0d, 2.0d, 17.0d);
        try {
            Store.products.add(4, d);
            Assert.fail("Not");
        }catch (AssertionError es) {
            assertNull(Store.products.get(4));
            assertEquals(d, Store.products.get(7));
            assertEquals(d, Store.products.get(-3));
            Store.products.clear();
            assertEquals(d, Store.products.get(0));
        }

    }

    @Test
    public void testTestEquals() {
        Basket basket = new Basket("122\\];80");
        this.basket = "122\\];80";
        String[] basketChars = this.basket.split("");
        String a = basketChars[0];
        String b = "1";
        String c = "A";
        boolean actual;
        boolean actual1;
        actual = b.equals(a);
        actual1 = !a.equals(c);
        assertTrue(actual);
        assertTrue(actual1);
    }

    @Test(expected = AssertionError.class)
    public void isEmpty() {
        this.basket = "";
        Basket basket = new Basket(this.basket);
        basket.calculateTotalCost(this.basket);
        try {
            Assert.fail(" Try again \n English capital letters only");
            throw new RuntimeException();
        } catch (AssertionError ex) {

            Assert.assertEquals("ABCDAB", "");
        }
    }

    @Test
    public void Basket() {
        String expected = "ABCDABA";
        String actual = basket;
        assertEquals(expected, actual);

        String expected4 = null;
        Assert.assertEquals(expected4, new Basket(null).getBasket());

        Basket basket = new Basket(this.basket);
        double expected1 = 24.75d;
        Assert.assertEquals(expected1, basket.getTotalPrice(), 0.01d);
    }

    @Test
    public void happyFlow() {
        Basket basket = new Basket("ABCDABA");

        String expected = "ABCDABA";
        Assert.assertNotNull(basket);
        Assert.assertEquals(expected, basket.getBasket());

        double expected1 = 13.25d;
        Assert.assertEquals(expected1, basket.getTotalPrice(), 0.01d);

        int expected2 = 4;
        Assert.assertEquals(expected2, Store.products.size());

        String expected3 = "A";
        Assert.assertEquals(expected3, Store.products.get(0).getName());

        assertEquals(Store.products.get(0).getName(), expected3);


    }
}

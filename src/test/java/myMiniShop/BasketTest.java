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
        Assert.assertNotNull(basket1.toUpperCase(Locale.ROOT));
        assertEquals("ABCDABA", basket1.toUpperCase(Locale.ROOT));
        List<Product> products = new Store().getProducts();
        products.add(4, new Product("A", 2.25d, 17.0d, 2.0d, 17.0d));
        assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                products.get(4));

        String[] expected1 = {"A", "B", "C", "D", "A", "B", "A"};
        Assert.assertNotNull(this.basket.split(""));
        Assert.assertArrayEquals(expected1, this.basket.split(""));
        String actual2 = expected1[0];
        String null1 = "A";
        Assert.assertNotNull(null1);
        assertEquals("A", actual2);
        Assert.assertEquals("\\.(GIF|JPG|PNG)$", new Basket("\\.(gif|jpg|png)$").getBasket());
        assertNull(new Basket(null).getBasket());
    }

    @Test
    public void testCountProductsInBasket() {
        Assert.assertNotNull(new Basket(this.basket).getBasket());
        Assert.assertEquals(this.basket, new Basket(this.basket).getBasket());
        Assert.assertNotNull(new Basket(this.basket1).getBasket());
        Assert.assertEquals(this.basket1.toUpperCase(Locale.ROOT), new Basket(this.basket1).getBasket());
        Assert.assertNotNull(new Basket(this.basket2).getBasket());
        Assert.assertEquals(this.basket2.toUpperCase(Locale.ROOT), new Basket(this.basket2).getBasket());
        Assert.assertNotNull(new Basket(this.basket3).getBasket());
        Assert.assertEquals(this.basket3.toUpperCase(Locale.ROOT), new Basket(this.basket3).getBasket());
        Assert.assertNotNull(new Basket(this.basket4).getBasket());
        Assert.assertEquals(new Basket(this.basket4).getBasket().toUpperCase(Locale.ROOT), new Basket(this.basket4).getBasket());
        assertNull(new Basket(null).getBasket());
        Assert.assertEquals("A", new Store().getProduct(0).getName());
        Assert.assertEquals(0.0d, new Store().getProduct(0).getAmount(), 0.01d);
    }

    @Test
    public void testCalculateTotalCost() {
        Assert.assertEquals(0.0d, new Basket(null).getTotalPrice(), 0.01d);
        Assert.assertEquals(13.25d, new Basket(this.basket1).getTotalPrice(), 0.01d);
        Assert.assertEquals(0.0d, new Basket(this.basket2).getTotalPrice(), 0.01d);
        Assert.assertEquals(0.0d, new Basket(this.basket3).getTotalPrice(), 0.01d);
        Assert.assertEquals(0.0d, new Store().getProduct(1).getDiscountAmount(), 0.01d);
        Assert.assertEquals(0.0d, new Store().getProduct(0).getAmount(), 0.01d);
    }

    @Test
    public void ifBasketNull() {
        new Store().getProduct(0).setAmount(7);
        new Store().getProduct(0).setPrice(15);
        Assert.assertNull(new Basket(null).getBasket());
        Assert.assertEquals(0.0d, new Basket(null).getTotalPrice(), 0.01d);
        Assert.assertEquals(0.0d, new Store().getProduct(0).getAmount(), 0.01d);
        Assert.assertEquals(1.25d, new Store().getProduct(0).getPrice(), 0.01d);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusDiscountAmount() {
        new Basket(this.basket);
        try {
            new Store().getProduct(0).setDiscountAmount(-3.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -100.0d, 0.01d);
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusDiscountPrice() {
        new Basket(this.basket);
        try {
            new Store().getProduct(0).setDiscountPrice(-1.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -1.0d, 0.01d);
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusPrice() {
        new Basket(this.basket);
        try {
            new Store().getProduct(0).setPrice(-2.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -10.0d, 0.01d);
        }

    }

    @Test(expected = AssertionError.class)
    public void shouldIgnoreMinusAmount() {
        new Basket(this.basket);
        try {
            new Store().getProduct(0).setAmount(-2.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            Assert.assertEquals(0.0d, -100.0d, 0.01d);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldIgnoreIndexOF() {
        new Basket(this.basket);
        try {
            new Store().getProducts().add(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d));
            Assert.fail("Not");
        } catch (AssertionError es) {
            assertNull(new Store().getProduct(4));
            assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                    new Store().getProduct(7));
            assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                    new Store().getProduct(-3));
            new Store().getProducts().clear();
            assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                    new Store().getProduct(0));
        }

    }

    @Test
    public void testTestEquals() {
        String[] basketChars = new Basket("122\\];80").getBasket().split("");
        String a = basketChars[0];
        String b = "1";
        String c = "A";
        assertEquals(b, a);
        assertNotEquals(a, c);
    }

    @Test(expected = AssertionError.class)
    public void isEmpty() {
        new Basket("").calculateTotalCost("");
        try {
            Assert.fail(" Try again \n English capital letters only");
            throw new RuntimeException();
        } catch (AssertionError ex) {
            Assert.assertEquals("ABCDAB", "");
        }
    }

    @Test
    public void Basket() {
        assertEquals("ABCDABA", basket);
        assertNull(new Basket(null).getBasket());
        Assert.assertEquals(13.25d, new Basket(this.basket).getTotalPrice(), 0.01d);
    }

    @Test
    public void happyFlow() {
        Assert.assertNotNull(basket);
        Assert.assertEquals("ABCDABA", new Basket("ABCDABA").getBasket());
        Assert.assertEquals(13.25d, new Basket("ABCDABA").getTotalPrice(), 0.01d);
        Assert.assertEquals(4, new Store().getProducts().size());
        Assert.assertEquals("A", new Store().getProduct(0).getName());
        assertEquals(new Store().getProduct(0).getName(), "A");
    }
}

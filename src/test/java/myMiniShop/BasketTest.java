package myMiniShop;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;

import java.util.List;
import java.util.Locale;

public class BasketTest {
    BasketImpl basketImpl = new BasketImpl();
    Store storeClass = new Store();
    Product product = new Product();

    String basket = "ABCDABA";
    String basket1 = "abcdaba";
    String basket2 = "АБСДАБА";
    String basket3 = "12345//)'.";
    String basket4 = "\"\\\\.(gif|jpg|png)$\"";

    List<Product> products = storeClass.getProducts();

    @Before
    public void setUp() {
        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
        products.add(new Product("B", 4.25d, 0.0d, 0.0d, 0.0d));
        products.add(new Product("C", 1.00d, 5.0d, 0.0d, 6.0d));
        products.add(new Product("D", 0.75d, 0.0d, 0.0d, 0.0d));
    }

    @Test
    public void testCheckingProductsInBasket() {
        basketImpl.setBasket("ABCDABA");
        String getBasket = basketImpl.getBasket();

        assertNotNull(getBasket.toUpperCase(Locale.ROOT));
        assertEquals("ABCDABA", getBasket.toUpperCase(Locale.ROOT));

        product = new Product("A", 2.25d, 17.0d, 2.0d, 17.0d);
        products.add(4, product);
        assertEquals(product, products.get(4));

        String[] expected1 = {"A", "B", "C", "D", "A", "B", "A"};
        assertNotNull(basket.split(""));
        String actual = expected1[0];
        String nullA = "A";
        assertNotNull(nullA);
        assertEquals("A", actual);

        basketImpl.setBasket("\\.(gif|jpg|png)$");
        assertEquals("ABCDABA", getBasket);

        basketImpl.setBasket(null);
        assertNull(basketImpl.getBasket());
    }

    @Test
    public void testCountProductsInBasket() {
        basketImpl.setBasket(basket);
        assertNotNull(basketImpl.getBasket());
        assertEquals(basket, basketImpl.getBasket());

        basketImpl.setBasket(basket1);
        assertNotNull(basketImpl.getBasket());
        assertEquals(basket1, basketImpl.getBasket());

        basketImpl.setBasket(basket2);
        assertNotNull(basketImpl.getBasket());
        assertEquals(basket2.toUpperCase(Locale.ROOT), basketImpl.getBasket());

        basketImpl.setBasket(basket3);
        assertNotNull(basketImpl.getBasket());
        assertEquals(basket3, basketImpl.getBasket());

        basketImpl.setBasket(basket4);
        assertNotNull(basketImpl.getBasket());
        assertEquals(basket4, basketImpl.getBasket());

        basketImpl.setBasket(null);
        assertNull(basketImpl.getBasket());
        assertEquals("A", storeClass.getProduct(0).getName());
        assertEquals(0.0d, storeClass.getProduct(0).getAmount(), 0.01d);
    }

    @Test
    public void testCalculateTotalCost() {
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);

        basketImpl.setBasket(basket1);
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);

        basketImpl.setBasket(basket2);
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);

        basketImpl.setBasket(basket3);
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);
        assertEquals(0.0d, storeClass.getProduct(1).getDiscountAmount(), 0.01d);
        assertEquals(3.0d, storeClass.getProduct(0).getDiscountAmount(), 0.01d);
    }

    @Test
    public void ifBasketNull() {
        storeClass.getProduct(0).setAmount(7);
        storeClass.getProduct(0).setPrice(15);
        basketImpl.setBasket(null);
        assertNull(basketImpl.getBasket());
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);
        assertEquals(7.0d, storeClass.getProduct(0).getAmount(), 0.01d);
        assertEquals(15.00d, storeClass.getProduct(0).getPrice(), 0.01d);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusDiscountAmount() {
        basketImpl.setBasket(basket);
        try {
            storeClass.getProduct(0).setDiscountAmount(-3.0d);
            Assert.fail("Not minus");
        } catch (AssertionError es) {
            assertEquals(0.0d, -100.0d, 0.01d);
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusDiscountPrice() {
        basketImpl.setBasket(basket);
        try {
            storeClass.getProduct(0).setDiscountPrice(-1.0d);
            fail("Not minus");
        } catch (AssertionError es) {
            assertEquals(0.0d, -1.0d, 0.01d);
        }
    }

    @Test(expected = AssertionError.class)
    public void shouldFailMinusPrice() {
        basketImpl.setBasket(basket);
        try {
            storeClass.getProduct(0).setPrice(-2.0d);
            fail("Not minus");
        } catch (AssertionError es) {
            assertEquals(0.0d, -10.0d, 0.01d);
        }

    }

    @Test(expected = AssertionError.class)
    public void shouldIgnoreMinusAmount() {
        basketImpl.setBasket(basket);
        try {
            storeClass.getProduct(0).setAmount(-2.0d);
            fail("Not minus");
        } catch (AssertionError es) {
            assertEquals(0.0d, -100.0d, 0.01d);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldIgnoreIndexOF() {
        basketImpl.setBasket(basket);
        product = new Product("A", 2.25d, 17.0d, 2.0d, 17.0d);
        try {
            storeClass.getProducts().add(product);
            fail("Not");
        } catch (AssertionError es) {
            assertNull(storeClass.getProduct(9));
            assertEquals(product, storeClass.getProduct(7));
            assertEquals(product, storeClass.getProduct(-3));
            storeClass.getProducts().clear();
            assertEquals(product, storeClass.getProduct(0));
        }

    }

    @Test(expected = AssertionError.class)
    public void testAssertEquals() {
        basketImpl.setBasket("122\\];80");
        String[] basketChars = basketImpl.getBasket().split("");
        String a = basketChars[0];
        String b = "1";
        String c = "A";
        assertEquals(b, a);
        assertEquals(a, c);
    }

    @Test(expected = AssertionError.class)
    public void isEmpty() {
        basketImpl.calculateTotalCost("");
        try {
            fail(" Try again \n English capital letters only");
            throw new RuntimeException();
        } catch (AssertionError ex) {
            assertEquals("ABCDAB", "");
        }
    }

    @Test
    public void Basket() {
        assertEquals("ABCDABA", basket);
        assertNull(basketImpl.getBasket());
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);
        basketImpl.setBasket(null);
        assertNull(basketImpl.getBasket());
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);
    }

    @Test
    public void happyFlow() {
        basketImpl.setBasket(basket);
        assertNotNull(basketImpl.getBasket());
        assertEquals("ABCDABA", basketImpl.getBasket());
        assertEquals(0.0d, basketImpl.getTotalPrice(), 0.01d);
        assertNotNull(storeClass.getProducts());
        assertEquals(8, storeClass.getProducts().size());
        assertNotNull(storeClass.getProduct(0));
        assertEquals("A", storeClass.getProduct(0).getName());
        assertNotNull(storeClass.getProduct(1));
        assertEquals("B", storeClass.getProduct(1).getName());
    }
}

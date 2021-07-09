package myMiniShop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StoreTest {
    Calculator testBasket;
    static List<Product> products = new ArrayList<>();
    BasketImpl basket = new BasketImpl();
    Store store = new Store();

    @Before
    public void setUp() {

        basket.calculateTotalCost("ABCDABA");

        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
    }

    @Test
    public void listProducts() {
        basket.setBasket("abcdaba");
        assertNotNull(basket.getBasket());
        assertEquals("abcdaba", basket.getBasket());
        assertEquals(1, products.size());
        assertEquals(1.25d, store.getProduct(0).getPrice(), 0.01d);
        assertEquals(1.25d, products.get(0).getPrice(), 0.01d);
    }

    @Test
    public void Store() {
        basket.setBasket("testBasket");
        double totalPrice = basket.getTotalPrice();

        assertEquals(4, store.getProducts().size());
        assertEquals(3.0d, store.getProduct(0).getDiscountAmount(), 0.1d);
        assertEquals(3.0d, store.getProduct(0).getDiscountPrice(), 0.1d);
        assertEquals(0.0d, store.getProduct(0).getAmount(), 0.1d);
        assertEquals(1.25d, store.getProduct(0).getPrice(), 0.1d);

        assertEquals("testBasket", basket.getBasket());
        assertEquals(13.25d, totalPrice, 0.01d);

        basket.setBasket("text, text, text");
        assertEquals("TEXT, TEXT, TEXT", basket.getBasket().toUpperCase(Locale.ROOT));

        basket.setBasket("aaa, aaa, aaa");
        assertEquals("aaa, aaa, aaa", basket.getBasket());
    }
}

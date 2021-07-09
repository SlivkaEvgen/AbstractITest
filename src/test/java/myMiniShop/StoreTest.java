package myMiniShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreTest {
    CalculatorImpl testBasket;
    static List<Product> products = new ArrayList<>();

    @Before
    public void setUp() {
        new Basket("ABCDABA");
        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
    }

    @Test
    public void listProducts() {
        new Basket("abcdaba").checkingProductsInBasket("abcdaba");
        Assert.assertNotNull(new Basket("testBasket").getBasket());
        Assert.assertEquals("TESTBASKET", new Basket("testBasket").getBasket());
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(products.get(0), products.get(0));
        Assert.assertEquals(1.25d, new Store().getProduct(0).getPrice(), 0.01d);
        Assert.assertEquals(1.25d, products.get(0).getPrice(), 0.01d);
    }

    @Test
    public void Store() {
        Assert.assertEquals("TESTBASKET", new Basket("testBasket").getBasket());
        Assert.assertEquals(5.5d, new Basket("testBasket").getTotalPrice(), 0.01d);
        Assert.assertEquals(4, new Store().getProducts().size());
        Assert.assertEquals(3.0d, new Store().getProduct(0).getDiscountAmount(), 0.1d);
        Assert.assertEquals(3.0d, new Store().getProduct(0).getDiscountPrice(), 0.1d);
        Assert.assertEquals(0.0d, new Store().getProduct(0).getAmount(), 0.1d);
        Assert.assertEquals(1.25d, new Store().getProduct(0).getPrice(), 0.1d);
        Assert.assertEquals(new Basket("text, text, text"), new Basket("text, text, text"));
        Assert.assertEquals(new Basket("AAA, AAA, AAA"), new Basket("aaa, aaa, aaa"));
    }
}

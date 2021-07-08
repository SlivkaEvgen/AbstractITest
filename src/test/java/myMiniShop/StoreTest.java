package myMiniShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreTest {
    String basket1 = "abcdaba";
    CalculatorImpl testBasket;
    static List<Product> products = new ArrayList<>();

    @Before
    public void setUp() {
        testBasket = new Basket("ABCDABA");
        Product product = new Product("A", 1.25d, 3.0d, 0.0d, 3.0d);
        products.add(product);
    }

    @Test
    public void listProducts() {
        Basket basket2 = new Basket("testBasket");
        testBasket.checkingProductsInBasket(basket1);
        String expected = "TESTBASKET";
        Assert.assertNotNull(basket2.getBasket());
        Assert.assertEquals(expected, basket2.getBasket());

        int expected1 = 1;
        Assert.assertEquals(expected1, products.size());

        Product abc = products.get(0);
        Assert.assertEquals(abc, products.get(0));

        double expected2 = 1.25d;
        double actual2 = Store.products.get(0).getPrice();

        double expected3 = 1.25d;
        Assert.assertEquals(expected3, actual2, 0.01d);
        Assert.assertEquals(expected2, products.get(0).getPrice(), 0.01d);
    }

    @Test
    public void Store() {
        Basket basket = new Basket("testBasket");
        String expected = "TESTBASKET";
        double expected1 = 5.5d;
        int expected2 = 4;
        double expected3 = 3.0d;
        double expected4 = 3.0d;
        double expected5 = 1.0d;
        double expected6 = 1.25d;

        Assert.assertEquals(expected, basket.getBasket());
        Assert.assertEquals(expected1, new Basket("testBasket").getTotalPrice(), 0.01d);
        Assert.assertEquals(expected2,Store.products.size());
        Assert.assertEquals(expected3,Store.products.get(0).getDiscountAmount(),0.1d);
        Assert.assertEquals(expected4,Store.products.get(0).getDiscountPrice(),0.1d);
        Assert.assertEquals(expected5,Store.products.get(0).getAmount(),0.1d);
        Assert.assertEquals(expected6,Store.products.get(0).getPrice(),0.1d);

        Basket result = new Basket("text, text, text");
        Assert.assertEquals(result, new Basket("text, text, text"));

        Basket result1 = new Basket("AAA, AAA, AAA");
        Assert.assertEquals(result1, new Basket("aaa, aaa, aaa"));


    }
}

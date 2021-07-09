package myMiniShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoProductTest {
    @Mock
    CalculatorImpl prod;
    @InjectMocks
    Store productNew = new Store(prod);
    static List<Product> products = new ArrayList<>();

    @Before
    public void setUp() {
        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
        products.add(new Product("B", 4.25d, 0.0d, 0.0d, 0.0d));
        products.add(new Product("C", 1.00d, 5.0d, 0.0d, 6.0d));
        products.add(new Product("D", 0.75d, 0.0d, 0.0d, 0.0d));
    }

    @Test
    public void mockitoGetAllProductsTest() {
        String result = mock(Product.class).getName() + ", " + mock(Product.class).getName();
        assertEquals(null, "null, null", result);

        Store store = mock(Store.class);
        when(store.getProduct(0)).thenReturn(products.get(0));
        assertEquals(products.get(0), store.getProduct(0));

        assertNull(mock(Store.class).getProduct(0));
        assertEquals(1.25d, new Store().getProduct(0).getPrice(), 0.01d);
        assertEquals(0.0d, new Store().getProduct(0).getAmount(), 0.01d);
        assertEquals(3.0d, new Store().getProduct(0).getDiscountAmount(), 0.01d);
        assertEquals(3.0d, new Store().getProduct(0).getDiscountPrice(), 0.01d);
        assertEquals("A", new Store().getProduct(0).getName());
        new Store().getProduct(3).setAmount(0);
        assertEquals(0, new Store().getProduct(3).getAmount(), 0.0d);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldIgnoreIndexOutOfBoundsException() {
        try {
            Product productNew = mock(Product.class);
            new Basket("basket");
            new Store().getProduct(5);
            Assert.fail("IndexOutOfBoundsException");
        } catch (AssertionError es) {
            assertNull(new Store().getProduct(4));
            assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                    new Store().getProduct(7));
            assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                    new Store().getProduct(-3));
            assertNull(new Store().getProduct(5));
            new Store().getProducts().clear();
            assertEquals(new Product("A", 2.25d, 17.0d, 2.0d, 17.0d),
                    new Store().getProduct(0));
        }
    }
}


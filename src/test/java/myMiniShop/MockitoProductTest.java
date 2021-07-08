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
    Product productNew = new Product(prod);
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
        Product productNew = mock(Product.class);

        String result = productNew.getName() + ", " + productNew.getName();

        assertEquals(null, "null, null", result);

        Store store = mock(Store.class);
        when(store.getAllProducts(0)).thenReturn(products.get(0));
        assertEquals(products.get(0), store.getAllProducts(0));

        Store store1 = mock(Store.class);
        assertNull(store1.getAllProducts(0));
        assertEquals(1.25d,Store.products.get(0).getPrice(),0.01d);
        assertEquals(1.0d,Store.products.get(0).getAmount(),0.01d);
        assertEquals(3.0d,Store.products.get(0).getDiscountAmount(),0.01d);
        assertEquals(3.0d,Store.products.get(0).getDiscountPrice(),0.01d);
        assertEquals("A",Store.products.get(0).getName());
        Store.products.get(3).setAmount(0);
        assertEquals(0,Store.products.get(3).getAmount(),0.0d);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldIgnoreIndexOutOfBoundsException() {
        Product productNew = mock(Product.class);
        Basket basket = new Basket("basket");
        Product d = new Product("A",2.25d,17.0d, 2.0d, 17.0d);
        try {
            Store.products.get(5);
            Assert.fail("IndexOutOfBoundsException");
        }catch (AssertionError es) {
            assertNull(Store.products.get(4));
            assertEquals(d, Store.products.get(7));
            assertEquals(d, Store.products.get(-3));
            assertEquals(null, Store.products.get(5));
            Store.products.clear();
            assertEquals(d, Store.products.get(0));
        }

    }
}


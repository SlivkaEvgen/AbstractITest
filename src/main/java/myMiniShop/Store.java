package myMiniShop;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class Store {
    static List<Product> products = new ArrayList<>();
    private static final Product productA;
    private static final Product productB;
    private static final Product productC;
    private static final Product productD;
    static {
        productA = new Product("A", 1.25d, 3.0d, 0.0d, 3.0d);
        productB = new Product("B", 4.25d, 0.0d, 0.0d, 0.0d);
        productC = new Product("C", 1.00d, 5.0d, 0.0d, 6.0d);
        productD = new Product("D", 0.75d, 0.0d, 0.0d, 0.0d);

        products.add(productA);
        products.add(productB);
        products.add(productC);
        products.add(productD);
    }
    public Store(){
    }
}


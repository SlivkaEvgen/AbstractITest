package myMiniShop;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class Store{
    static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
        products.add(new Product("B", 4.25d, 0.0d, 0.0d, 0.0d));
        products.add(new Product("C", 1.00d, 5.0d, 0.0d, 6.0d));
        products.add(new Product("D", 0.75d, 0.0d, 0.0d, 0.0d));
    }
    public Store(){
    }
}
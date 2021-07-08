package myMiniShop;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Contract;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
class Store extends Product{
    protected static List<Product> products = new ArrayList<>();
    static
    {
        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
        products.add(new Product("B", 4.25d, 0.0d, 0.0d, 0.0d));
        products.add(new Product("C", 1.00d, 5.0d, 0.0d, 6.0d));
        products.add(new Product("D", 0.75d, 0.0d, 0.0d, 0.0d));
    }
    @Contract(pure = true)
    public Store(){
        super("A", 0.0d, 0.0d, 0.0d, 0.0d);
    }
}
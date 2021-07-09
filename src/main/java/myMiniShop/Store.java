package myMiniShop;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
class Store extends Product {
    private List<Product> products = new ArrayList<>();

    {
        products.add(new Product("A", 1.25d, 3.0d, 0.0d, 3.0d));
        products.add(new Product("B", 4.25d, 0.0d, 0.0d, 0.0d));
        products.add(new Product("C", 1.00d, 5.0d, 0.0d, 6.0d));
        products.add(new Product("D", 0.75d, 0.0d, 0.0d, 0.0d));
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public Store(CalculatorImpl prod) {
        super("A", 0.0d, 0.0d, 0.0d, 0.0d);
    }
    public Store(){
    }
}
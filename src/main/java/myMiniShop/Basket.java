package myMiniShop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode()
@Data
public final class Basket implements CalculatorImpl {
    @Setter
    private String basket;
    @Setter
    private double totalPrice;
    private List<Product> basketProducts = new Store().getProducts();

    public Basket(final String basket) {
        this.basket = basket;
        if (this.basket != null) {
            this.basket = basket.toUpperCase();
            checkingProductsInBasket(this.basket);
        }
    }

    @Override
    public void checkingProductsInBasket(final String basket) {
        int count = 0;
        for (String basketChar : this.basket.split("")) {
            for (Product product : basketProducts) {
                if (product.getName().equals(basketChar)) {
                    count++;
                }
            }
        }
        if (count > 0) {
            countProductsInBasket(this.basket);
        }
    }

    @Override
    public void countProductsInBasket(String basket) {
        if (basket != null) {
            double count = 0.0d;
            for (Product product : basketProducts) {
                product.setAmount(0.0d);
                for (String basketChar : this.basket.split("")) {
                    if (basketChar.equals(product.getName())) {
                        product.setAmount(product.getAmount() + count + 1);
                    }
                }
                count = 0.0d;
            }
            calculateTotalCost(this.basket);
        }
    }

    @Override
    public void calculateTotalCost(String basket) {
        for (Product product : basketProducts) {
            double amount = product.getAmount();
            double price = product.getPrice();
            double discountPrice = product.getDiscountPrice();
            double discountAmount = product.getDiscountAmount();
            int a = (int) (amount / discountAmount);
            if (amount < discountPrice) {
                totalPrice += amount * price;
            } else {
                totalPrice += price * (amount - discountAmount * a) + discountPrice * a;
            }
        }
    }

    public String toString() {
        return "Basket{" +
                "basket='" + basket + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

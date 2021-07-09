package myMiniShop;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode()
@Data
public final class BasketImpl implements Calculator {
    private String basket;
    private double totalPrice;
    private List<Product> basketProducts = new Store().getProducts();

    @Override
    public String calculateTotalCost(String basket) {
        totalPrice = 0.0d;
        this.basket = basket;
        if (basket != null && isBasketCorrect()) {
            countProductsInBasket();
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
        return "Your Basket : '" + basket + "'\n" +
                "Total Price = " + totalPrice + "$\n";
    }

    private boolean isBasketCorrect() {
        basket = basket.toUpperCase();
        for (String basketChar : basket.split("")) {
            for (Product product : basketProducts) {
                product.setAmount(0.0d);
                if (product.getName().equals(basketChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void countProductsInBasket() {
        if (basket != null) {
            double count = 0.0d;
            for (Product product : basketProducts) {
                for (String basketChar : basket.split("")) {
                    if (basketChar.equals(product.getName())) {
                        product.setAmount(product.getAmount() + count + 1);
                    }
                }
                count = 0.0d;
            }
        }
    }
}

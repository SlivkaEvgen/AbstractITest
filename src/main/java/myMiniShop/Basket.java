package myMiniShop;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Locale;

@EqualsAndHashCode()
@Data
public final class Basket implements CalculatorInter {
    @lombok.Setter
    private String basket;
    @lombok.Setter
    private double totalPrice;

    public Basket(final String basket) {
        this.basket = basket;
        checkingProductsInBasket(basket);
    }

    @Override
    public void checkingProductsInBasket(String basket) {
        if (basket == null){
            return;
        }
        this.basket = basket.toUpperCase(Locale.ROOT);
        String[] basketChars = this.basket.split("");
        boolean s = true;
        for (int i = 0; i <= basketChars.length - 1; i++) {
            String a = basketChars[i];
            for (int j = 0; j <= Store.products.size() - 1; j++) {
                String b = Store.products.get(j).getName();
                if (b.equals(a)) {
                    s = true;
                    break;
                } else s = false;
            }
        }
        if (s) {
            calculateTotalCost(this.basket);
        } else {
            System.out.println("Try again! basket has not products");
        }
    }

    @Override
    public void countProductsInBasket(String basket) {
        this.basket = basket;
        if (this.basket == null) {
            System.out.println("Try again! basket = NULL, \n countProductsInBasket()");
        } else {
            double count = 0.0d;
            String[] basketChars = this.basket.split("");
            for (int i = 0; i <= Store.products.size() - 1; i++) {
                Store.products.get(i).setAmount(0.0d);
                for (int j = 0; j <= basketChars.length - 1; j++) {
                    String basketChar = basketChars[j];
                    String name = Store.products.get(i).getName();
                    if (basketChar.equals(name))
                        Store.products.get(i).setAmount(Store.products.get(i).getAmount() + count + 1);
                }
                count = 0.0d;
            }
        }
    }

    @Override
    public void calculateTotalCost(String basket) {
        countProductsInBasket(basket);
        for (int i = 0; i <= Store.products.size() - 1; i++) {
            double amount = Store.products.get(i).getAmount();
            double discountPrice = Store.products.get(i).getDiscountPrice();
            double price = Store.products.get(i).getPrice();
            double discountAmount = Store.products.get(i).getDiscountAmount();
            double result;
            if (amount < discountPrice) {
                price = amount * price;
                result = price;
                totalPrice += result;
            }
            if (amount >= discountAmount) {
                int a = (int) (amount / discountAmount);
                amount = amount - discountAmount * a;
                discountPrice = discountPrice * a;
                result = price * amount + discountPrice;
                totalPrice += result;
            }
        }
    }
}

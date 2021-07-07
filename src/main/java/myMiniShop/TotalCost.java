package myMiniShop;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@Data
public class TotalCost extends CalculatorInter {
    private double totalPrice;
    private String basket;

    @Override
    void checkingProductsInBasket(String basket) {
                if (this.basket != null && !this.basket.contains("[a-zA-Z]+")) {
            this.basket = basket.toUpperCase(Locale.ROOT);
        } else {
            System.out.println("Try again! basket == NULL "
                    + "\n checkingProductsInBasket-14");
        }
    }
    @Override
    void countProductsInBasket(String basket) {
        this.basket = basket;
        if (this.basket == null) {
            System.out.println("Try again! basket = NULL, \n countProductsInBasket-31");
        } else {
            double count = 0.0d;
            String[] basketChars = this.basket.split("");
            for (int i = 0; i <= Store.products.size() - 1; i++) {
                Store.products.get(i).setAmount(0.0d);
                //  String d = myMiniShop.Store.products.get(i).toString();
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
    public double calculateTotalCost(String basket) {
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
        return totalPrice;
    }
}

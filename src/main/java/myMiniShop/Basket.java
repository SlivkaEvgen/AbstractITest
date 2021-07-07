package myMiniShop;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
final class Basket extends TotalCost {
    private String basket;
    private double totalPrice;
    private TotalCost totalCost = new TotalCost();

    private Basket(final String basket) {
        this.basket = basket;
        totalCost.calculateTotalCost(basket);
        totalCost.checkingProductsInBasket(basket);
        totalCost.calculateTotalCost(basket);
    }
}

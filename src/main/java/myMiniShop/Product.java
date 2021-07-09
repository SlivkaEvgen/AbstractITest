package myMiniShop;

import lombok.Data;
import lombok.Setter;

@Data
class Product {
    @Setter
    private String name;
    @Setter
    private double price;
    @Setter
    private double discountPrice;
    @Setter
    private double amount;
    @Setter
    private double discountAmount;
    protected Product(String name, double price, double discountPrice, double amount, double discountAmount) {
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.amount = amount;
        this.discountAmount = discountAmount;
    }

    Product() {
    }
}
package myMiniShop;
import lombok.Data;

@Data
class Product {
    @lombok.Setter
    private  String name;
    @lombok.Setter
    private  double price;
    @lombok.Setter
    private  double discountPrice;
    @lombok.Setter
    private  double amount;
    @lombok.Setter
    private  double discountAmount;
    protected Product(String name, double price, double discountPrice, double amount, double discountAmount) {
        this.name = name;
        this.price =  price;
        this.discountPrice = discountPrice;
        this.amount = amount;
        this.discountAmount = discountAmount;
    }
}


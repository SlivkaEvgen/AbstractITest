package myMiniShop;

public class Main {
    public static void main(String[] args) {
        BasketImpl basketImpl = new BasketImpl();
        System.out.println(basketImpl.calculateTotalCost("ABCDABA"));
    }
}

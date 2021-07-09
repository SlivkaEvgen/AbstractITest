import myMiniShop.Basket;

public class Main {
    public static void main(String[] args) {
       System.out.println(new Basket("\\.(gif|jpg|png)$") + "\n");
        System.out.println(new Basket("ABCDABA") + "\n");
        System.out.println(new Basket(null) + "\n");
        System.out.println(new Basket("12335/.86") + "\n");
        System.out.println(new Basket("1abcdaba1427"));
        System.out.println(new Basket("A  B  A  B"));
        System.out.println(new Basket("d  d  d  d"));
        System.out.println(new Basket("d  d  d  d"));
    }
}

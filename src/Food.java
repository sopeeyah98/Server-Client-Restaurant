public class Food {
    String name;
    Double price;
    boolean cooked;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
        this.cooked = false;
    }

    public void cook() {
        this.cooked = true;
    }
}

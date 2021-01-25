public class Robot {
    public Customer customer;

    public Robot(Customer customer) {
        this.customer = customer;
    }

    public void takeOrder(Food food) {
        this.customer.addOrder(food);
    }

    public Customer deliverOrder() {
        return this.customer;
    }

    public String bringFood() {
        String str = "I have brought you: ";
        for(Food food: customer.tab)
            str += "\n" + food.name;
        return str;
    }

    public String generateCheck() {
        String str = "Total price comes out to $";
        Double total = 0.0;
        for (Food food: this.customer.tab)
            total += food.price;
        str += Double.toString(total);
        return str;
    }

}

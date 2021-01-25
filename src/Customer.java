import java.net.Socket;
import java.util.ArrayList;

public class Customer extends Thread{
    public int table_num;
    public ArrayList<Food> tab;
    private Socket socket;

    public Customer(Socket socket) {
        this.socket = socket;
    }

    public void assignTable(int table_num) {
        this.table_num = table_num;
        this.tab = new ArrayList<>();
    }

    public void addOrder(Food food) {
        this.tab.add(food);
    }
}

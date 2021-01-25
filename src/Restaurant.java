import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Restaurant {
    public static void main(String[] args) throws IOException {
        String restaurant_name = "Cibo Italiano";

        // Menu
        HashMap<String, Double> menu = new HashMap<>();
        menu.put("fried calamari", 15.0);
        menu.put("avocado rolls", 12.0);
        menu.put("chips and dip", 11.0);
        menu.put("pasta", 20.0);
        menu.put("sandwich", 18.0);
        menu.put("pizza", 20.0);
        menu.put("ribs", 30.0);
        menu.put("ice cream", 5.0);
        menu.put("pizookie", 10.0);
        menu.put("cheese cake", 12.0);
        menu.put("tiramisu", 13.0);
        menu.put("lemonade", 5.0);
        menu.put("soda", 5.0);
        menu.put("coffee", 5.0);
        menu.put("water", 0.0);

        // has 100 tables
        Queue<Integer> table_num = new LinkedList<>();
        for (int i = 1; i <= 100; i++)
            table_num.add(i);

        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);

        try ( ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        ) {

            while(true) {
                Socket customerSocket = serverSocket.accept();
                Customer customer = new Customer(customerSocket);
                System.out.println("socket connected");
                BufferedReader in = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
                PrintWriter out = new PrintWriter(customerSocket.getOutputStream(), true);
                out.println("Welcome to " + restaurant_name);

                if (!table_num.isEmpty()) { // table is not all taken
                    customer.assignTable(table_num.poll());
                    Robot robot = new Robot(customer);
                    out.println("Here is our MENU!");
                    for (String name : menu.keySet()) {
                        String price = Double.toString(menu.get(name));
                        out.println(name + ": " + price);
                    }
                    out.println("When you are ready to order, please type the name of your desired food one at a time");
                    out.println("When you are done ordering, please type 'd'");
                    ArrayList<Food> order = new ArrayList<>();
                    String str = in.readLine();

                    while (!str.equals("d")) {
                        if (menu.containsKey(str))
                            robot.takeOrder(new Food(str, menu.get(str)));
                        else
                            out.println("incorrect input; check you spelling and re-enter:");
                        str = in.readLine();
                    }
                    out.println("I'll be right back with your food.");
                    robot.customer = toChef(robot.deliverOrder());
                    out.println(robot.bringFood());

                    out.println("Please type 'c' when you are ready for your check.");
                    str = in.readLine();
                    while (!str.equals("c")) {
                        out.println("Please type 'c'");
                        str = in.readLine();
                    }
                    out.println(robot.generateCheck());
                    customerSocket.close();
                } else
                    out.println("Sorry, Tables are all full at the moment.");
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public static Customer toChef(Customer customer) {
        for (int i = 0; i < customer.tab.size(); i++)
            customer.tab.get(i).cook();
        return customer;
    }
}
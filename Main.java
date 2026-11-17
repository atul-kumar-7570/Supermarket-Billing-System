
import java.util.*;
import java.io.*;

class BillItem {
    String name;
    int quantity;
    int price;

    BillItem(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

public class Main {

    static Queue<String> queue = new LinkedList<>();
    static HashMap<String, Integer> prices = new HashMap<>();
    static ArrayList<String> bills = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        prices.put("rice", 60);
        prices.put("milk", 30);
        prices.put("bread", 40);

        loadBills();

        System.out.println("Previous bills loaded: " + bills.size());

        while (true) {

            System.out.println("\n===== SUPERMARKET BILLING SYSTEM =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Bill Next Customer");
            System.out.println("3. View Queue");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    billCustomer();
                    break;

                case 3:
                    viewQueue();
                    break;

                case 4:
                    saveBills();
                    System.out.println("Bills saved successfully.");
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Add customer to queue
    static void addCustomer() {

        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        queue.add(name);

        System.out.println(name +
                " added to billing queue. Position: " + queue.size());
    }

    // Bill next customer
    static void billCustomer() {

        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        String customer = queue.poll();

        BillItem[] items = new BillItem[100];
        Stack<BillItem> undoStack = new Stack<>();

        int count = 0;
        int total = 0;

        System.out.println("\nBilling Customer: " + customer);
        System.out.println("Enter product name with quantity.");
        System.out.println("Example: Rice x1");
        System.out.println("Type Undo to remove last item.");
        System.out.println("Type Done to finish billing.");

        while (true) {

            System.out.print("Item: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("Done")) {
                break;
            }

            // Undo last scanned item
            if (input.equalsIgnoreCase("Undo")) {

                if (undoStack.isEmpty()) {
                    System.out.println("No item to undo.");
                } else {
                    undoStack.pop();
                    count--;
                    System.out.println("Last item removed.");
                }

                continue;
            }

            // Read product and quantity
            String[] data = input.split("(?i)\\s*x\\s*");

            String product = data[0].trim().toLowerCase();
            int quantity = 1;

            if (data.length > 2) {
                System.out.println("Invalid input format.");
                continue;
            }

            try {
                if (data.length == 2) {
                    quantity = Integer.parseInt(data[1].trim());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity.");
                continue;
            }

            if (!prices.containsKey(product)) {
                System.out.println("Item " + data[0].trim()
                        + " not found in price list.");
                continue;
            }

            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                continue;
            }

            if (count == items.length) {
                System.out.println("Bill item limit reached.");
                continue;
            }

            BillItem item = new BillItem(
                    product, quantity, prices.get(product));

            items[count++] = item;
            undoStack.push(item);

            System.out.println(data[0].trim() +
                    " added successfully.");
        }

        // Calculate and display bill
        StringBuilder bill = new StringBuilder();

        bill.append("Bill for ").append(customer).append(": ");

        for (int i = 0; i < count; i++) {

            BillItem item = items[i];

            int amount = item.quantity * item.price;
            total += amount;

            if (i > 0) {
                bill.append(" | ");
            }

            bill.append(capitalize(item.name))
                .append(" x").append(item.quantity)
                .append(" = ").append(amount);
        }

        bill.append(" | Total ").append(total);

        System.out.println("\n" + bill);

        if (count == 0) {
            System.out.println("No items purchased.");
        }

        bills.add(bill.toString());
    }

    // View remaining customers
    static void viewQueue() {

        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("\nCurrent Billing Queue:");

        int position = 1;

        for (String name : queue) {
            System.out.println(position + ". " + name);
            position++;
        }
    }

    // Load previous bills from file
    static void loadBills() {

        try (BufferedReader br =
                new BufferedReader(new FileReader("bills.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                bills.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("New billing file will be created.");
        } catch (IOException e) {
            System.out.println("Error loading bills.");
        }
    }

    // Save all bills to file
    static void saveBills() {

        try (PrintWriter pw =
                new PrintWriter(new FileWriter("bills.txt"))) {

            for (String bill : bills) {
                pw.println(bill);
            }

        } catch (IOException e) {
            System.out.println("Error saving bills.");
        }
    }

    // Capitalize product name
    static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase()
                + name.substring(1);
    }
}

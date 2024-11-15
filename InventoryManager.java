import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class InventoryManager {
    public static final class Item {
        private final int id;
        private final String name;
        private int quantity;
        private double price;
        public Item(final int id, final String name, int quantity, double price) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setQuantity(final int quantity) {
            this.quantity = quantity;
        }

        public void setPrice(final double price) {
            this.price = price;
        }
        public String toString() {
            return "Item [ID=" + id + ", Name=" + name + ", Quantity=" + quantity + ", Price=" + price + "]";
        }
    }
    public static final class Inventory {
        private final List<Item> items;

        public Inventory() {
            this.items = new ArrayList<>();
        }

        public void addItem(final Item item) {
            items.add(item);
        }

        public void removeItem(final int id) {
            items.removeIf(item -> item.getId() == id);
        }

        public Item getItem(final int id) {
            for (Item item : items) {
                if (item.getId() == id) {
                    return item;
                }
            }
            return null;
        }

        public void updateItemQuantity(final int id, final int quantity) {
            final Item item = getItem(id);
            if (item != null) {
                item.setQuantity(quantity);
            }
        }

        public void displayItems() {
            if (items.isEmpty()) {
                System.out.println("No items in inventory.");
            } else {
                for (final Item item : items) {
                    System.out.println(item);
                }
            }
        }
    }
    private static final Inventory inventory = new Inventory();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nInventory Management System:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item Quantity");
            System.out.println("4. Display Items");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            final int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    updateItemQuantity();
                    break;
                case 4:
                    inventory.displayItems();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter item ID: ");
        final int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter item name: ");
        final String name = scanner.nextLine();
        System.out.print("Enter item quantity: ");
        final int quantity = scanner.nextInt();
        System.out.print("Enter item price: ");
        final double price = scanner.nextDouble();

        final Item item = new Item(id, name, quantity, price);
        inventory.addItem(item);
        System.out.println("Item added successfully.");
    }

    private static void removeItem() {
        System.out.print("Enter item ID to remove: ");
        final int id = scanner.nextInt();
        inventory.removeItem(id);
        System.out.println("Item removed successfully.");
    }

    private static void updateItemQuantity() {
        System.out.print("Enter item ID to update: ");
        final int id = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        final int quantity = scanner.nextInt();

        inventory.updateItemQuantity(id, quantity);
        System.out.println("Item quantity updated successfully.");
    }
}

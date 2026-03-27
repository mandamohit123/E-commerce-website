
import java.util.ArrayList;
import java.util.Scanner;

// Product class
class Product {

    private int id;
    private String name;
    private double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Price: $" + price);
    }
}

// Customer class
class Customer {

    private String name;
    private ArrayList<Product> cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cart.add(product);
        System.out.println("Added to cart: " + product.getId());
    }

    public void removeFromCart(int productId) {
        boolean removed = false;
        for (Product p : cart) {
            if (p.getId() == productId) {
                cart.remove(p);
                System.out.println("Removed from cart: " + productId);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Product not found in cart.");
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n-- Cart Items --");
        for (Product p : cart) {
            p.display();
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }
}

// Main class
public class OnlineShoppingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample product catalog
        Product[] products = {
            new Product(1, "Laptop", 800),
            new Product(2, "Smartphone", 500),
            new Product(3, "Headphones", 100),
            new Product(4, "Book", 30)
        };

        // Customer
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName);

        int choice;
        do {
            System.out.println("\n=== Online Shopping Menu ===");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Calculate Total");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n-- Available Products --");
                    for (Product p : products) {
                        p.display();
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID to add to cart: ");
                    int addId = scanner.nextInt();
                    Product productToAdd = findProductById(products, addId);
                    if (productToAdd != null) {
                        customer.addToCart(productToAdd);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID to remove from cart: ");
                    int removeId = scanner.nextInt();
                    customer.removeFromCart(removeId);
                    break;

                case 4:
                    customer.viewCart();
                    break;

                case 5:
                    double total = customer.calculateTotal();
                    System.out.printf("Total Amount: $%.2f%n", total);
                    break;

                case 6:
                    System.out.println("Exiting Online Shopping System...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Helper method to find product by ID
    public static Product findProductById(Product[] products, int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}

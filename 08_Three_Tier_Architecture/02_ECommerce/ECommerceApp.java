// Presentation Tier
import java.util.Scanner;

public class ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ECommerceService service = new ECommerceService();
        
        // Add sample products
        service.addProduct(1, "Laptop", 999.0);
        service.addProduct(2, "Mouse", 29.0);
        service.addProduct(3, "Keyboard", 79.0);
        
        String user = null;
        
        while (true) {
            if (user == null) {
                System.out.println("\n=== E-Commerce ===");
                System.out.println("1. Login");
                System.out.println("2. View Products");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                if (choice == 1) {
                    System.out.print("Username: ");
                    user = sc.nextLine();
                    System.out.println("Welcome " + user + "!");
                } else if (choice == 2) {
                    service.viewProducts();
                } else if (choice == 3) {
                    break;
                }
            } else {
                System.out.println("\n=== Welcome " + user + " ===");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Logout");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                
                if (choice == 1) {
                    service.viewProducts();
                } else if (choice == 2) {
                    System.out.print("Product ID: ");
                    int id = sc.nextInt();
                    service.addToCart(user, id);
                    System.out.println("Added to cart!");
                } else if (choice == 3) {
                    service.viewCart(user);
                } else if (choice == 4) {
                    service.checkout(user);
                } else if (choice == 5) {
                    user = null;
                    System.out.println("Logged out!");
                }
            }
        }
        sc.close();
    }
}

// Data Tier
import java.util.*;

public class ECommerceDAO {
    private HashMap<Integer, String> products = new HashMap<>();
    private HashMap<Integer, Double> prices = new HashMap<>();
    private HashMap<String, List<Integer>> cart = new HashMap<>();
    
    public void addProduct(int id, String name, double price) {
        products.put(id, name);
        prices.put(id, price);
    }
    
    public void viewProducts() {
        System.out.println("\n--- Products ---");
        for (int id : products.keySet()) {
            System.out.println(id + ". " + products.get(id) + " - $" + prices.get(id));
        }
    }
    
    public void addToCart(String user, int productId) {
        if (!cart.containsKey(user)) {
            cart.put(user, new ArrayList<>());
        }
        cart.get(user).add(productId);
    }
    
    public void viewCart(String user) {
        if (!cart.containsKey(user) || cart.get(user).isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        double total = 0;
        for (int id : cart.get(user)) {
            System.out.println(products.get(id) + " - $" + prices.get(id));
            total += prices.get(id);
        }
        System.out.println("Total: $" + total);
    }
    
    public void clearCart(String user) {
        cart.put(user, new ArrayList<>());
    }
}

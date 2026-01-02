// Application Tier
public class ECommerceService {
    ECommerceDAO dao = new ECommerceDAO();
    
    public void addProduct(int id, String name, double price) {
        dao.addProduct(id, name, price);
    }
    
    public void viewProducts() {
        dao.viewProducts();
    }
    
    public void addToCart(String user, int productId) {
        dao.addToCart(user, productId);
    }
    
    public void viewCart(String user) {
        dao.viewCart(user);
    }
    
    public void checkout(String user) {
        dao.viewCart(user);
        System.out.println("\nOrder placed successfully!");
        dao.clearCart(user);
    }
}

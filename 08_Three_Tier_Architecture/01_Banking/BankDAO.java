// Data Tier
import java.util.HashMap;

public class BankDAO {
    private HashMap<String, Double> accounts = new HashMap<>();
    
    public void createAccount(String accNo, double initialBalance) {
        accounts.put(accNo, initialBalance);
    }
    
    public double getBalance(String accNo) {
        return accounts.getOrDefault(accNo, -1.0);
    }
    
    public void updateBalance(String accNo, double newBalance) {
        accounts.put(accNo, newBalance);
    }
}

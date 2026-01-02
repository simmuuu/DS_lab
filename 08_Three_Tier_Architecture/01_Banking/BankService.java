// Application Tier
public class BankService {
    BankDAO dao = new BankDAO();
    
    public void createAccount(String accNo, double initialBalance) {
        dao.createAccount(accNo, initialBalance);
    }
    
    public double checkBalance(String accNo) {
        return dao.getBalance(accNo);
    }
    
    public boolean deposit(String accNo, double amount) {
        double balance = dao.getBalance(accNo);
        if (balance >= 0) {
            dao.updateBalance(accNo, balance + amount);
            return true;
        }
        return false;
    }
    
    public boolean withdraw(String accNo, double amount) {
        double balance = dao.getBalance(accNo);
        if (balance >= amount && balance >= 0) {
            dao.updateBalance(accNo, balance - amount);
            return true;
        }
        return false;
    }
    
    public boolean transfer(String fromAcc, String toAcc, double amount) {
        double fromBalance = dao.getBalance(fromAcc);
        double toBalance = dao.getBalance(toAcc);
        if (fromBalance >= amount && fromBalance >= 0 && toBalance >= 0) {
            dao.updateBalance(fromAcc, fromBalance - amount);
            dao.updateBalance(toAcc, toBalance + amount);
            return true;
        }
        return false;
    }
}

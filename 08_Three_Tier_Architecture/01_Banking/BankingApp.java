// Presentation Tier
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();
        
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                System.out.print("Initial Balance: ");
                double bal = sc.nextDouble();
                service.createAccount(acc, bal);
                System.out.println("Account created!");
                
            } else if (choice == 2) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                double bal = service.checkBalance(acc);
                System.out.println(bal >= 0 ? "Balance: " + bal : "Account not found!");
                
            } else if (choice == 3) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                boolean success = service.deposit(acc, amt);
                System.out.println(success ? "Deposit successful" : "Account not found!");
                
            } else if (choice == 4) {
                System.out.print("Account Number: ");
                String acc = sc.nextLine();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                boolean success = service.withdraw(acc, amt);
                System.out.println(success ? "Withdraw successful" : "Insufficient balance or account not found!");
                
            } else if (choice == 5) {
                System.out.print("From Account: ");
                String from = sc.nextLine();
                System.out.print("To Account: ");
                String to = sc.nextLine();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                boolean success = service.transfer(from, to, amt);
                System.out.println(success ? "Transfer successful" : "Transfer failed!");
                
            } else if (choice == 6) {
                System.out.println("Thank you!");
                break;
            }
        }
        sc.close();
    }
}

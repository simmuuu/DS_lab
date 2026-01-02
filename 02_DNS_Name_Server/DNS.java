import java.net.*;
import java.util.Scanner;

// DNS - All in main
public class DNS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== DNS Lookup ===");
            System.out.println("1. Hostname to IP");
            System.out.println("2. IP to Hostname");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 3) break;
            
            try {
                if (choice == 1) {
                    System.out.print("Enter hostname: ");
                    String hostname = sc.nextLine();
                    InetAddress addr = InetAddress.getByName(hostname);
                    System.out.println("IP: " + addr.getHostAddress());
                    
                } else if (choice == 2) {
                    System.out.print("Enter IP: ");
                    String ip = sc.nextLine();
                    InetAddress addr = InetAddress.getByName(ip);
                    System.out.println("Hostname: " + addr.getHostName());
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}

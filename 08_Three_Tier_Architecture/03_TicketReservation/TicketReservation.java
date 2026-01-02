// Presentation Tier
import java.util.Scanner;

public class TicketReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketService service = new TicketService();
        String user = null;
        
        while (true) {
            if (user == null) {
                System.out.println("\n=== Ticket Reservation ===");
                System.out.println("1. Login");
                System.out.println("2. View Available Seats");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                if (choice == 1) {
                    System.out.print("Username: ");
                    user = sc.nextLine();
                    System.out.println("Welcome " + user + "!");
                } else if (choice == 2) {
                    service.viewSeats();
                } else if (choice == 3) {
                    break;
                }
            } else {
                System.out.println("\n=== Welcome " + user + " ===");
                System.out.println("1. View Seats");
                System.out.println("2. Book Ticket");
                System.out.println("3. Cancel Ticket");
                System.out.println("4. My Bookings");
                System.out.println("5. Logout");
                System.out.print("Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                if (choice == 1) {
                    service.viewSeats();
                } else if (choice == 2) {
                    System.out.print("Seat Number (1-10): ");
                    int seat = sc.nextInt();
                    boolean success = service.bookTicket(user, seat);
                    System.out.println(success ? "Booking successful!" : "Seat not available!");
                } else if (choice == 3) {
                    System.out.print("Seat Number: ");
                    int seat = sc.nextInt();
                    boolean success = service.cancelTicket(user, seat);
                    System.out.println(success ? "Ticket cancelled!" : "Invalid seat or not your booking!");
                } else if (choice == 4) {
                    service.myBookings(user);
                } else if (choice == 5) {
                    user = null;
                    System.out.println("Logged out!");
                }
            }
        }
        sc.close();
    }
}

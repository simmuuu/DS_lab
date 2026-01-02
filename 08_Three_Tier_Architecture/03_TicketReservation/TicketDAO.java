// Data Tier
import java.util.HashMap;

public class TicketDAO {
    private HashMap<Integer, String> seats = new HashMap<>();
    
    public TicketDAO() {
        // Initialize 10 seats
        for (int i = 1; i <= 10; i++) {
            seats.put(i, null);
        }
    }
    
    public void viewSeats() {
        System.out.println("\n--- Seat Status ---");
        for (int i = 1; i <= 10; i++) {
            System.out.println("Seat " + i + ": " + (seats.get(i) == null ? "Available" : "Booked"));
        }
    }
    
    public String getSeatOwner(int seatNo) {
        return seats.get(seatNo);
    }
    
    public void bookSeat(int seatNo, String user) {
        seats.put(seatNo, user);
    }
    
    public void cancelSeat(int seatNo) {
        seats.put(seatNo, null);
    }
    
    public void viewUserBookings(String user) {
        System.out.println("\n--- Your Bookings ---");
        boolean found = false;
        for (int seat : seats.keySet()) {
            if (user.equals(seats.get(seat))) {
                System.out.println("Seat " + seat);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found!");
        }
    }
}

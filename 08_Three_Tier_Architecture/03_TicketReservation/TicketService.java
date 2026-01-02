// Application Tier
public class TicketService {
    TicketDAO dao = new TicketDAO();
    
    public void viewSeats() {
        dao.viewSeats();
    }
    
    public boolean bookTicket(String user, int seatNo) {
        if (seatNo >= 1 && seatNo <= 10 && dao.getSeatOwner(seatNo) == null) {
            dao.bookSeat(seatNo, user);
            return true;
        }
        return false;
    }
    
    public boolean cancelTicket(String user, int seatNo) {
        if (user.equals(dao.getSeatOwner(seatNo))) {
            dao.cancelSeat(seatNo);
            return true;
        }
        return false;
    }
    
    public void myBookings(String user) {
        dao.viewUserBookings(user);
    }
}

# 3-Tier Architecture - Ticket Reservation System

## Overview
Ticket/seat reservation system implementing 3-tier architecture with seat availability, booking, and cancellation features.

## Files
- **TicketReservation.java** - Presentation Tier (UI)
- **TicketService.java** - Application Tier (Business Logic)
- **TicketDAO.java** - Data Tier (Data Access)

## Architecture
```
┌─────────────────────┐
│  Presentation Tier  │  TicketReservation.java
│  (User Interface)   │  - Login, View Seats, Book, Cancel
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│  Application Tier   │  TicketService.java
│  (Business Logic)   │  - Booking validation, Cancellation
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│     Data Tier       │  TicketDAO.java
│   (Data Access)     │  - Seat storage (HashMap)
└─────────────────────┘
```

## Code Explanation

### TicketReservation.java (Presentation)
```java
// Guest: Login, View Seats, Exit
// User: View Seats, Book, Cancel, My Bookings, Logout
```

### TicketService.java (Application)
```java
viewSeats()                    → Display all seat status
bookTicket(user, seatNo)       → Validate and book seat
cancelTicket(user, seatNo)     → Validate ownership and cancel
myBookings(user)               → Show user's bookings
```

### TicketDAO.java (Data)
```java
HashMap<Integer, String> seats  → Seat number to username (null = available)
// Initialize 10 seats as available
getSeatOwner(seatNo)           → Returns who booked seat
bookSeat(seatNo, user)         → Marks seat as booked
cancelSeat(seatNo)             → Marks seat as available
```

## Features
- View Seat Availability
- Book Ticket (seat)
- Cancel Booking
- View My Bookings

## How to Run
```bash
javac *.java
java TicketReservation
```

---

## Viva Questions

### Basic Questions
1. **What is a reservation system?**
   - System to book resources (seats, rooms, tickets) in advance.

2. **How is seat availability tracked?**
   - HashMap with seat number as key; null value means available.

3. **What validation is done before booking?**
   - Check if seat exists (1-10) and is available (null owner).

4. **What validation is done before cancellation?**
   - Check if the seat belongs to the user requesting cancellation.

5. **What is the data structure for seats?**
   - HashMap<Integer, String> where Integer=seat number, String=username.

### Intermediate Questions
6. **How to prevent double booking?**
   - Check seat availability before booking; in real systems, use database locks.

7. **How would you add pricing?**
   - Add price HashMap or different prices for different seat categories.

8. **How to add seat categories (VIP, Regular)?**
   - Add category field, different price tiers, separate availability checks.

9. **How to implement waiting list?**
   - Queue data structure for users when all seats are full.

10. **How to add booking confirmation?**
    - Generate booking ID, send email/SMS confirmation.

### Advanced Questions
11. **What is race condition in booking?**
    - Two users trying to book same seat simultaneously; one should fail.

12. **How to handle concurrent bookings?**
    - Database transactions, optimistic/pessimistic locking.

13. **What is optimistic vs pessimistic locking?**
    - Optimistic: Check version before update
    - Pessimistic: Lock row during entire transaction

14. **How to implement seat selection UI?**
    - Grid layout showing seat positions, colors for availability.

15. **How to scale for high traffic (concert tickets)?**
    - Queue system, distributed locks, caching, horizontal scaling.

16. **What is overbooking?**
    - Booking more than capacity (airlines do this), requires waitlist management.

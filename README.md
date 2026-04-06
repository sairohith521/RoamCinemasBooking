# BookMyShow Clone (Java Console App)

A simple Java console-based movie ticket booking system.

## Features

- List available movies with cost and rating
- Book tickets by selecting seat numbers
- Prevent booking already occupied seats
- Save bookings to `tickets.txt` with:
  - date
  - movie name
  - ticket count
  - seat numbers
  - total cost
- End-of-day summary:
  - total tickets sold per movie
  - total collection per movie
  - grand total
  - reset `tickets.txt` and seat occupancy for next day

## Project Structure

- `BookMyShow/Movie.java` - movie model and seat map logic
- `BookMyShow/MovieManager.java` - movie/file operations and end-of-day summary
- `BookMyShow/Ticket.java` - ticket model and booking persistence
- `BookMyShow/TicketBookingSystem.java` - main menu and booking flow
- `movies.txt` - movie master data + seat occupancy
- `tickets.txt` - booking records

## Requirements

- Java JDK 11 or higher

## Compile and Run

From repository root:

```bash
cd BookMyShow
javac Movie.java MovieManager.java Ticket.java TicketBookingSystem.java User.java Utils.java
cd ..
java BookMyShow.TicketBookingSystem
```

## Menu (Current)

1. Book Ticket  
2. Modify Movie  
3. End of Day  
4. Exit

## Notes

- If you are using PowerShell and want to remove compiled files:

```powershell
Get-ChildItem -Recurse -Filter *.class | Remove-Item -Force
```

## Updates - April 6, 2026
- Fixed ticket confirmation email details to include:
  - actual selected seat numbers (instead of hardcoded values)
  - booking time



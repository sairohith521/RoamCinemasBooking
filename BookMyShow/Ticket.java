package BookMyShow;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ticket {
    String userId;
    String movieName;
    int numberOfTickets;
    String date;
    int cost;
    ArrayList<Integer> seatNumbers;

    Ticket(String userId, String movieName, int numberOfTickets,int cost, ArrayList<Integer> seatNumbers) {
        this.userId = userId;
        this.movieName = movieName;
        this.numberOfTickets = numberOfTickets;
        this.date = LocalDate.now().toString();
        this.cost=cost;
        this.seatNumbers = seatNumbers;
    }

    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("tickets.txt", true);
            fw.write("User ID: " + userId + ", Date: " + date + ", Movie: " + movieName + ", Tickets: " + numberOfTickets + ", Seat Numbers: " + seatNumbers + ", Total Cost: " + cost*numberOfTickets + "\n");
            fw.close();
            System.out.println();
            System.out.println(" Ticket saved successfully!");
            System.out.println();
            System.out.println("<-------Cost of the Ticket is "+cost*numberOfTickets+ ". ------------>");
        } catch (IOException e) {
            System.out.println(" Error saving ticket.");
        }
    }
}

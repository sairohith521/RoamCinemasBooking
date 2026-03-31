package BookMyShow;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ticket {
    String movieName;
    int numberOfTickets;
    String date;
    ArrayList<Integer> seatNumbers;

    Ticket(String movieName, int numberOfTickets, ArrayList<Integer> seatNumbers) {
        this.movieName = movieName;
        this.numberOfTickets = numberOfTickets;
        this.seatNumbers = seatNumbers;
        this.date = LocalDate.now().toString();
    }

    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("tickets.txt", true);
            fw.write("Date: " + date + ", Movie: " + movieName + ", Tickets: " + numberOfTickets + ", Seat Numbers: " + seatNumbers + "\n");
            fw.close();
            System.out.println(" Ticket saved successfully!");
        } catch (IOException e) {
            System.out.println(" Error saving ticket.");
        }
    }
}

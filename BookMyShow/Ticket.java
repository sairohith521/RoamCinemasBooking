package BookMyShow;
import java.io.*;
import java.time.LocalDate;

public class Ticket {
    String movieName;
    int numberOfTickets;
    String date;

    Ticket(String movieName, int numberOfTickets) {
        this.movieName = movieName;
        this.numberOfTickets = numberOfTickets;
        this.date = LocalDate.now().toString();
    }

    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter("tickets.txt", true);
            fw.write("Date: " + date + ", Movie: " + movieName + ", Tickets: " + numberOfTickets + "\n");
            fw.close();
            System.out.println(" Ticket saved successfully!");
        } catch (IOException e) {
            System.out.println(" Error saving ticket.");
        }
    }
}

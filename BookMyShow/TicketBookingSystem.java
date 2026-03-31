package BookMyShow;
import java.util.ArrayList;
import java.util.Scanner;
public class TicketBookingSystem {
    public static void main(String[] args) throws Exception{
       System.out.println("                Welcome to ROAM Cinemas          ");
       System.out.println("--------------------------------------------------------      ");
       //Adding Movies to the list 
        // ArrayList<Movie> list = new ArrayList<>();

        // list.add(new Movie("Avengers", 200, 8.5));
        // list.add(new Movie("Interstellar", 250, 9.0));
        // list.add(new Movie("Charlie777", 500,9.7));
        // list.add(new Movie("KantaraCh2", 550,8.9));
        // list.add(new Movie("Dhurandhar", 650,9.2));
        // list.add(new Movie("KGF2", 450,8.5));

        // Write
        // MovieManager.writeMovies(list);

        // Read
        MovieManager.movies = MovieManager.readMovies();
       

        //Printing the list of movies.
        MovieManager.printMovies();
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("1. Book Ticket");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1) {
                System.out.println("Enter the serial number of the Movie u want to watch..");
                int movieIndex=sc.nextInt();
                //Checking validity.
                if(movieIndex<1 ||movieIndex>MovieManager.movies.size()){
                    System.out.println("Movie not available");
                    continue;
                 }
                Movie chosenMovie=MovieManager.movies.get(movieIndex - 1);
                System.out.println("How many tickets you want to Book..");
                int noOfTickets=sc.nextInt();
                
                // Check if enough tickets are available
                if (chosenMovie.getAvailableTicketCount() < noOfTickets) {
                    System.out.println("❌ Not enough tickets available! Only " + chosenMovie.getAvailableTicketCount() + " tickets left.");
                    continue;
                }

                // Book the tickets and get seat numbers
                ArrayList<Integer> bookedSeats = chosenMovie.bookTickets(noOfTickets);
                
                // Calculate total amount
                double totalAmount = chosenMovie.getCost() * noOfTickets;
                
                Ticket t = new Ticket(chosenMovie.getName(), noOfTickets, bookedSeats);
                t.saveToFile();
                
                // Display total amount
                System.out.println("✅ Booking successful!");
                System.out.println("Your total amount is: Rs." + totalAmount);

            }else if (choice == 2) {
                System.out.println("Thank you! Exiting...");
                break;
            } else {
                System.out.println(" Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}

package BookMyShow;
import java.util.Scanner;
public class TicketBookingSystem {
    public static void main(String[] args){
       System.out.println("            Cinema Ticket Booking system       ");
       System.out.println("--------------------------------------------------------      ");
       //Adding Movies to the list 
        MovieManager.addMovie("Charlie777", 500,9.7);
        MovieManager.addMovie("KantaraCh2", 550,8.9);
        MovieManager.addMovie("Dhurandhar", 650,9.2);
        MovieManager.addMovie("KGF2", 450,8.5);

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

                Ticket t = new Ticket(chosenMovie.getName(), noOfTickets);
                t.saveToFile();

            } else if (choice == 2) {
                System.out.println("Thank you! Exiting...");
                break;
            } else {
                System.out.println(" Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}

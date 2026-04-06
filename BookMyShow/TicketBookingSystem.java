package BookMyShow;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        // Print movie list initially.
        MovieManager.printMovies();
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("1. Book Ticket");
            System.out.println("2. Modify Movie");
            System.out.println("3. Show Your Tickets");
            System.out.println("4. End of Day");
            System.out.println("5. Exit");
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
                System.out.println("Available Seats: ");
                chosenMovie.showSeats();
                System.out.println("How many tickets you want to Book..");
                int noOfTickets=sc.nextInt();
                sc.nextLine();
                if(noOfTickets<=0){
                    System.out.println("Invalid quantity of tickets entered!");
                    continue;
                }
                if(chosenMovie.freeSeatsCount()<noOfTickets ){
                    System.out.println("only "+chosenMovie.freeSeatsCount()+" seats Available!");
                    continue;
                }
                System.out.println("Enter seat numbers (space separated):");

               String input = sc.nextLine(); 
               String[] parts = input.split(" ");
               ArrayList<Integer> list = new ArrayList<>();
                for (String p : parts) {
                    list.add(Integer.parseInt(p));
                }
                if(!MovieManager.updateMovieSeats(chosenMovie.getName(),list,true)){
                       System.out.println("Invalid Seats Selected!");
                }
                else if(list.size() != noOfTickets) {
                    System.out.println("Please enter exactly " + noOfTickets + " seat numbers!");
                    continue;
                }
                else{
                  System.out.print("Enter your email: ");
                  String email = sc.nextLine();
                  String userId = User.getOrCreateUserId(email);
                  System.out.println("Your User ID: " + userId);
                MovieManager.updateSeatsInFile(chosenMovie.getName(), chosenMovie.getSeats());
                Ticket t = new Ticket(userId, chosenMovie.getName(), noOfTickets,chosenMovie.getCost(),list);
                t.saveToFile();
                String seats = list.toString();
                String Date = LocalDate.now().toString();
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                String details = "User ID: " + userId +
                 "\nMovie: " + chosenMovie.getName() +
                 "\nSeats: " + seats +
                 "\nDate: " + Date +
                 "\nTime: " + time;
                EmailService.sendEmail(email, details);
                MovieManager.printMovies();
                }

            }else if(choice==2){
            MovieManager.printMovies();
            System.out.println("Select the Serial number of the Movie you want to modify!");
            int movieIndex=sc.nextInt();
             //Checking validity.
                if(movieIndex<1 ||movieIndex>MovieManager.movies.size()){
                    System.out.println("Movie not available");
                    continue;
                 }
            Movie chosenMovie=MovieManager.movies.get(movieIndex - 1);
            if(chosenMovie.audiIsEmpty()){
            System.out.println("Enter movie name, cost, rating (space separated):");
            String name = sc.next(); 
            int cost = sc.nextInt();
            double rating = sc.nextDouble();
            Movie newMovie=new Movie(name, cost, rating);
            MovieManager.modifyMovie(movieIndex-1,newMovie);
            MovieManager.printMovies();
            }
            else{
                System.out.println("Screen is  occupied by "+chosenMovie.getName()+" movie. Please try tomorrow.");
            }


            }else if(choice==3){
              System.out.print("Enter your User ID: ");
              String userId = sc.nextLine();
              MovieManager.showTicketsByUser(userId);
            }else if(choice==4){
              MovieManager.endOfDay();

        //priyam code .......


            }else if (choice == 5) {
                System.out.println("Thank you! Exiting...");
                break;
            } else {
                System.out.println(" Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}

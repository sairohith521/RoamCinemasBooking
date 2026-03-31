package BookMyShow;

import java.util.ArrayList;

public class Movie {
    private String name;
    private int cost;
    private double ratings;
    private boolean[][] seats = new boolean[6][10];
    public  Movie(String name,int cost) throws Exception{
        this.name=name;
        this.cost=cost;
        this.ratings=0;
        this.seats = MovieManager.readSeats(name);
    }
    public  Movie(String name,int cost,double ratings)throws Exception{
        this.name=name;
        this.cost=cost;
        this.ratings=ratings;
        this.seats = MovieManager.readSeats(name);
    }
    public String getName(){
        return this.name;
    }
    public boolean[][] getSeats(){
        return seats;
    }
    public double getRatings(){
        return this.ratings;
    }
    public int getCost(){
       return this.cost;
    }
    public void setCost(int cost) {
        if (cost > 0) {
            this.cost = cost;
        }else{
           System.out.println("Ticket cost Can't be negative.");
        }
    }
     public void setRatings(double ratings) {
         if (ratings >= 0 && ratings <= 10) {
            this.ratings = ratings;
        } else {
            System.out.println("Invalid rating! Must be between 0 and 10.");
          }
    }
    public void showSeats() {

    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 10; j++) {

            if (seats[i][j]) {
                System.out.print(" X ");
            } else {
                int seatNumber = i * 10 + j + 1;
                System.out.printf("%2d ", seatNumber);
            }
        }
        System.out.println();
    }
}

public boolean updateSeats(ArrayList<Integer> seatNumbers, boolean book) {

    // Step 1: Validate ALL first
    for (int seat : seatNumbers) {

        int row = (seat - 1) / 10;
        int col = (seat - 1) % 10;

        if (row < 0 || row >= 6 || col < 0 || col >= 10) {
            System.out.println("Invalid seat: " + seat);
            return false;
        }

        if (book && seats[row][col]) {
            System.out.println("Already booked: " + seat);
            System.out.println("Enter available seats to confirm booking");
            return false;
        }

        if (!book && !seats[row][col]) {
            System.out.println("Already empty: " + seat);
            return false;
        }
    }

    // Step 2: Apply changes ONLY if all valid
    for (int seat : seatNumbers) {

        int row = (seat - 1) / 10;
        int col = (seat - 1) % 10;

        seats[row][col] = book;
    }

    return true;
}
public int freeSeatsCount(){
 int count = 0;

    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 10; j++) {

            if (!seats[i][j]) {
                count++;
            }
        }
    }

    return count;
}
}

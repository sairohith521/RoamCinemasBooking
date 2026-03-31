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
public void updateSeats(ArrayList<Integer> seatNumbers, boolean book) {

    for (int seat : seatNumbers) {

        int row = (seat - 1) / 10;
        int col = (seat - 1) % 10;

        // Validation
        if (row < 0 || row >= 6 || col < 0 || col >= 10) {
            System.out.println("Invalid seat: " + seat);
            continue;
        }

        if (book) {
            // Booking
            if (seats[row][col]) {
                System.out.println("Already booked: " + seat);
            } else {
                seats[row][col] = true;
                System.out.println("Booked: " + seat);
            }
        } else {
            // Cancel
            if (!seats[row][col]) {
                System.out.println("Already empty: " + seat);
            } else {
                seats[row][col] = false;
                System.out.println("Cancelled: " + seat);
            }
        }
    }
}
}

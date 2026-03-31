package BookMyShow;
import java.util.ArrayList;

public class Movie {
    private String name;
    private int cost;
    private double ratings;
    private ArrayList<Integer> availableTickets;
    
    public  Movie(String name,int cost){
        this.name=name;
        this.cost=cost;
        this.ratings=0;
        initializeTickets();
    }
    public  Movie(String name,int cost,double ratings){
        this.name=name;
        this.cost=cost;
        this.ratings=ratings;
        initializeTickets();
    }
    
    private void initializeTickets() {
        this.availableTickets = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            availableTickets.add(i);
        }
    }
    public String getName(){
        return this.name;
    }
    public double getRatings(){
        return this.ratings;
    }
    public int getCost(){
       return this.cost;
    }
    public ArrayList<Integer> getAvailableTickets() {
        return this.availableTickets;
    }
    public ArrayList<Integer> bookTickets(int numberOfTickets) {
        ArrayList<Integer> bookedTickets = new ArrayList<>();
        if (availableTickets.size() >= numberOfTickets) {
            for (int i = 0; i < numberOfTickets; i++) {
                bookedTickets.add(availableTickets.remove(0));
            }
        }
        return bookedTickets;
    }
    public int getAvailableTicketCount() {
        return availableTickets.size();
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
}

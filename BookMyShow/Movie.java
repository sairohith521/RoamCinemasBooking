package BookMyShow;
public class Movie {
    private String name;
    private int cost;
    private double ratings;
    public  Movie(String name,int cost){
        this.name=name;
        this.cost=cost;
        this.ratings=0;
    }
    public  Movie(String name,int cost,double ratings){
        this.name=name;
        this.cost=cost;
        this.ratings=ratings;
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

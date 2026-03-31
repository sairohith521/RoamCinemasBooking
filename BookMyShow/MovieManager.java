package BookMyShow;
import java.util.ArrayList;

public class MovieManager {

    static ArrayList<Movie> movies = new ArrayList<>();

    public static void addMovie(String name,int cost,double rating) {
        Movie m = new Movie(name,cost,rating);
        movies.add(m);
        System.out.println("Movie added successfully!");
    }
    public static void printMovies(){
        System.out.println("       Movie   "+"     Cost  "+"Ratings");
        System.out.println("    ---------- "+"     ----  "+"-------");
        int index = 1;
        for(Movie movie:MovieManager.movies){
            String movieName=Utils.formatedName(movie.getName());
            System.out.println(index++ +" : "+movieName+"  ->  "+movie.getCost()+"     "+ movie.getRatings());
        }
    }
}

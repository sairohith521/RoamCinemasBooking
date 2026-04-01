package BookMyShow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieManager {

    static ArrayList<Movie> movies = new ArrayList<>();
    public static void addMovie(String name,int cost,double rating) throws Exception {
        Movie m = new Movie(name,cost,rating);
        movies.add(m);
        System.out.println("Movie added successfully!");
    }
    public static void printMovies(){
        System.out.println("       Movie   "+"          Cost  "+"  Ratings");
        System.out.println("    ---------- "+"          ----  "+"  -------");
        int index = 1;
        for(Movie movie:MovieManager.movies){
            String movieName=Utils.formatedName(movie.getName());
            System.out.printf("%2d : " +movieName+"  ->  "+movie.getCost()+"     "+ movie.getRatings(), index++);
            System.out.println();
        }
    }
    public static ArrayList<Movie> readMovies() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("movies.txt"));

        ArrayList<Movie> list = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");

            String name = p[0];
            int cost = Integer.parseInt(p[1]);
            double rating = Double.parseDouble(p[2]);

            list.add(new Movie(name, cost, rating));
        }

        br.close();
        return list;
    }
    public static void writeMovies(ArrayList<Movie> list) throws Exception {
                       BufferedWriter bw = new BufferedWriter(new FileWriter("movies.txt",true));

                       for (Movie m : list) {
                        bw.write(m.getName() + "," + m.getCost() + "," + m.getRatings());
                        bw.newLine();
                       }

                      bw.close();
    }
    public static void deleteMovie(String movieName) throws Exception {

    //  Read all movies
    ArrayList<Movie> list = readMovies();

    //  Remove matching movie
    Iterator<Movie> it = list.iterator();

    while (it.hasNext()) {
        Movie m = it.next();

        if (m.getName().equalsIgnoreCase(movieName)) {
            it.remove();
        }
    }

    // Rewrite file
    writeMovies(list);
}
public static Boolean updateMovieSeats(String movieName, ArrayList<Integer> seatNumbers, boolean book) {

        for (Movie m : movies) {

            if (m.getName().equalsIgnoreCase(movieName)) {

                Boolean flag=m.updateSeats(seatNumbers, book);
                if(flag){
                    System.out.println("Seats updated for " + movieName);
                    return true;
                }
                else {
                    System.out.println("Booking failed");
                    return false;
                }
                
            }
        }

        System.out.println("Movie not found!");
        return false;
    }
    public static boolean[][] readSeats(String movieName) throws Exception {

    BufferedReader br = new BufferedReader(new FileReader("movies.txt"));

    String line;

    while ((line = br.readLine()) != null) {

        String[] parts = line.split(",");

        if (parts[0].equalsIgnoreCase(movieName)) {

            boolean[][] seats = new boolean[6][10];
            if (parts.length < 4) {
                    // No seat data → initialize empty seats
                     br.close();
                    return new boolean[6][10];
                }
            String[] seatData = parts[3].trim().split(" ");

            int index = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 10; j++) {
                    seats[i][j] = seatData[index++].equals("1");
                }
            }

            br.close();
            return seats;
        }
    }

    br.close();
    return null; // movie not found
}
public static void updateSeatsInFile(String movieName, boolean[][] seats) throws Exception {

    BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
    ArrayList<String> lines = new ArrayList<>();

    String line;

    while ((line = br.readLine()) != null) {

        String[] parts = line.split(",");

        if (parts[0].equalsIgnoreCase(movieName)) {

            // rebuild line with updated seats
            StringBuilder sb = new StringBuilder();

            sb.append(parts[0]).append(",")
              .append(parts[1]).append(",")
              .append(parts[2]).append(",");

            // add updated seat data
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 10; j++) {
                    sb.append(seats[i][j] ? "1" : "0").append(" ");
                }
            }

            lines.add(sb.toString().trim());

        } else {
            lines.add(line); // keep unchanged
        }
    }

    br.close();

    // rewrite file
    BufferedWriter bw = new BufferedWriter(new FileWriter("movies.txt"));

    for (String l : lines) {
        bw.write(l);
        bw.newLine();
    }

    bw.close();
}
}

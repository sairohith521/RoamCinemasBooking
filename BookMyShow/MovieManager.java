package BookMyShow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MovieManager {

    static ArrayList<Movie> movies = new ArrayList<>();
    public static void addMovie(String name,int cost,double rating) throws Exception {
        Movie m = new Movie(name,cost,rating);
        movies.add(m);
        System.out.println("Movie added successfully!");
    }
    public static void printMovies() throws Exception{
        movies=MovieManager.readMovies();
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
            if (line.trim().isEmpty()) continue;
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

public static void endOfDay() throws Exception {
    Map<String, Integer> movieTicketCount = new LinkedHashMap<>();
    Map<String, Integer> movieCollection = new LinkedHashMap<>();
    int grandTotal = 0;

    BufferedReader br = new BufferedReader(new FileReader("tickets.txt"));
    String line;

    while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) {
            continue;
        }

        String[] parts = line.split(", ");
        String movieName = null;
        int tickets = 0;
        int totalCost = 0;

        for (String part : parts) {
            if (part.startsWith("Movie: ")) {
                movieName = part.substring("Movie: ".length()).trim();
            } else if (part.startsWith("Tickets: ")) {
                tickets = Integer.parseInt(part.substring("Tickets: ".length()).trim());
            } else if (part.startsWith("Total Cost: ")) {
                totalCost = Integer.parseInt(part.substring("Total Cost: ".length()).trim());
            }
        }

        if (movieName != null) {
            movieTicketCount.put(movieName, movieTicketCount.getOrDefault(movieName, 0) + tickets);
            movieCollection.put(movieName, movieCollection.getOrDefault(movieName, 0) + totalCost);
            grandTotal += totalCost;
        }
    }
    br.close();

    System.out.println();
    System.out.println("--------------- End Of Day Summary ---------------");
    int index = 1;
    for (String movieName : movieCollection.keySet()) {
        System.out.println(index++ + ". " + movieName + " = " + movieTicketCount.get(movieName)
                + " Tickets = " + movieCollection.get(movieName));
    }
    System.out.println("Grand Total = " + grandTotal);
    System.out.println("--------------------------------------------------");

    BufferedWriter ticketWriter = new BufferedWriter(new FileWriter("tickets.txt", false));
    ticketWriter.write("");
    ticketWriter.close();

    for (Movie movie : movies) {
        boolean[][] seats = movie.getSeats();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                seats[i][j] = false;
            }
        }
        updateSeatsInFile(movie.getName(), seats);
    }

    System.out.println("Tickets and seat bookings reset for next day.");
}
public static void modifyMovie(int index, Movie newMovie) throws Exception {

    ArrayList<String> lines = new ArrayList<>();

    //  Read all lines
    BufferedReader br = new BufferedReader(new FileReader("movies.txt"));
    String line;

    while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) continue;
        lines.add(line);
    }
    br.close();

    //  Validate index
    if (index < 0 || index >= lines.size()) {
        System.out.println("Invalid movie index");
        return;
    }

    //  Create new movie line (with seats)
    StringBuilder seats = new StringBuilder();
    for (int i = 0; i < 60; i++) {
        seats.append("0 ");
    }

    String newLine = newMovie.getName() + "," +
                     newMovie.getCost() + "," +
                     newMovie.getRatings() + "," +
                     seats.toString().trim();

    //  Replace line at index
    lines.set(index, newLine);

    //  Write back (overwrite file)
    BufferedWriter bw = new BufferedWriter(new FileWriter("movies.txt"));

    for (String l : lines) {
        bw.write(l);
        bw.newLine();
    }

    bw.close();

    System.out.println("Movie updated successfully!");
}
}

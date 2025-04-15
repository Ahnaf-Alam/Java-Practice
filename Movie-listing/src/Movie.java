import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    private String movieName;
    private String movieCategory;
    private double rating;

    private List<Movie> movieList = new ArrayList<>();

    public Movie(String movieName, String movieCategory, double rating) {
        this.movieName = movieName;
        this.movieCategory = movieCategory;
        this.rating = rating;
    }

    public Movie() {}
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Movie> getAllMovie() {
        return movieList;
    }

    public String addMovie(Movie movie) {
        movieList.add(movie);

        return movie.getMovieName() + "added";
    }

    public List<Movie> searchMovie(String movieName) {
        return  this.movieList.stream().filter(movie -> movie.getMovieName().equals(movieName)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", movieCategory='" + movieCategory + '\'' +
                ", rating=" + rating +
                '}';
    }
}

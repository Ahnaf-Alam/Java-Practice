import auth.Login;
import auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        System.out.println("Welcome to Movie Application");

        List<User> users = new ArrayList<>();

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Test-1", "Thriller", 4.5));
        movies.add(new Movie("test-2", "Crime", 2.5));
        movies.add(new Movie("test-3", "Crime", 4));

        boolean isUserLoggedIn = doLoginUser(users);

        if(isUserLoggedIn) {
            System.out.println("User logged in Successfully");
            boolean wantToExist = false;
            System.out.println("======= Movie List ========");
            Movie movie = new Movie();
            movie.addMovie(movies.get(0));
            movie.addMovie(movies.get(1));
            movie.addMovie(movies.get(2));

            movie.getAllMovie().stream().forEach(movie1 -> System.out.println(movie1));

            while (!wantToExist) {
                System.out.println("search move: ");
                String searchMovie = obj.nextLine();

                List<Movie> movieList = movie.searchMovie(searchMovie);

                movieList.stream().forEach(movie1 -> System.out.println(movie1));

                System.out.println("Do you want to exist");
                wantToExist = obj.nextBoolean();
            }
        }

    }

    private static boolean doLoginUser(List<User> users) {
        Scanner obj = new Scanner(System.in);

        int dontHaveAccount = 0;

        System.out.println("Do you have account: press 1 if have otherwise press 0");
        dontHaveAccount = obj.nextInt();

        if(dontHaveAccount == 0) {
            signInUser(users);
        }

        Scanner loginObj = new Scanner(System.in);

        System.out.println("======== Login ========");
        System.out.println("Please enter your username: ");
        String username = loginObj.nextLine();

        System.out.println("Password: ");
        String password = loginObj.nextLine();

        Login login = new Login(username, password);
        boolean isLoggedIn = login.doLogin(login, users);

        if (!isLoggedIn) {
            System.out.println("Username or password is incorrect");
            doLoginUser(users);
        }
        return true;
    }

    private static User signInUser(List<User> users) {
        Scanner obj = new Scanner(System.in);

        System.out.println("--------------Sign In----------------");

        System.out.println("username: ");
        String username = obj.nextLine();

        System.out.println("First name: ");
        String firstName = obj.nextLine();

        System.out.println("Last name: ");
        String lastName = obj.nextLine();

        System.out.println("Phone number: ");
        String phoneNumber = obj.nextLine();

        System.out.println("Password: ");
        String password = obj.nextLine();

        User user = new User(username, firstName, lastName, phoneNumber, password, "USER");
        users.add(user);

        System.out.println("User add successfully");
        return user;
    }
}
public class Main {
    public static void main(String[] args) {
        Object lock = new Object();
        Runnable r1 = new ThreadExample(lock);
        Runnable r2 = new ThreadExample(lock);
        new Thread(r1, "even").start();
        new Thread(r2, "odd").start();
    }
}
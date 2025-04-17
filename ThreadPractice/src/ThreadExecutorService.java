import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorService implements Runnable{

    @Override
    public void run() {
        printNumber(10);
    }
    public void printNumber(int n) {
        for(int i=0;i<n;i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " value is: " + i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++) {
            ThreadExecutorService threadExecutorService = new ThreadExecutorService();
            executorService.execute(threadExecutorService);
        }
    }


}

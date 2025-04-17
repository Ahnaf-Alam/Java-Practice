import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadExampleWithES {

    static int count = 0;

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void printOddEvenNumber() {
        for(int i=0;i<=10;i++) {
            int finalI = i;
            CompletableFuture<Integer> oddCompletableFuture = CompletableFuture.completedFuture(i)
                    .thenApplyAsync(x -> {
                        if(x % 2 != 0) {
                            System.out.println("Thread name: " + Thread.currentThread().getName() + " value: " + x);
                        }
                        return finalI;
                    }, executorService);
            oddCompletableFuture.join();

            CompletableFuture<Integer> evenCompletableFuture = CompletableFuture.completedFuture(i)
                    .thenApplyAsync(x -> {
                        if(x % 2 == 0) {
                            System.out.println("Thread name: " + Thread.currentThread().getName() + " value: " + x);
                        }
                        return finalI;
                    }, executorService);
            evenCompletableFuture.join();
        }

        executorService.shutdown();
    }

    public static void main(String[] args) {
        ThreadExampleWithES threadExampleWithES = new ThreadExampleWithES();
        threadExampleWithES.printOddEvenNumber();
    }
}

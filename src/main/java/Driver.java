import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch prev = null;
        for (int i = 0; i < 6; i++) {
            CountDownLatch curr = new CountDownLatch(DummyRunner.MAX_COUNT - 1);
            executorService.submit(new DummyRunner(i, curr, prev));
            prev = curr;
        }
        try {
            if (prev != null) {
                prev.await();
            }
        }
        catch (Exception ex) {

        }
        finally {
            executorService.shutdown();
        }
    }
}

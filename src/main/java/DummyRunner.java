import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DummyRunner implements Runnable {
    private CountDownLatch curr, prev;
    private AtomicInteger counter = new AtomicInteger(0);
    public final static Integer MAX_COUNT = 10;
    private Integer id;
    public DummyRunner(Integer id, CountDownLatch curr, CountDownLatch prev) {
        this.curr = curr;
        this.prev = prev;
        this.id = id;
    }
    public void run() {
        Integer k = counter.incrementAndGet();
        while (k < MAX_COUNT) {

            try {
                if (prev != null) {
                    prev.await();
                }
                System.out.println(id + " count is " + k);
                if (this.curr != null) {
                    this.curr.countDown();
                }
                k = counter.incrementAndGet();
                Thread.sleep(1000);
            }
            catch (Exception ex) {

            }
        }
    }
}

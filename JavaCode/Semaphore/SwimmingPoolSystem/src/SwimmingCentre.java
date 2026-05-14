import java.util.concurrent.Semaphore;

public class SwimmingCentre {

    final int POOL_CAPACITY = 4;

    private Semaphore semaphore;
    private SwimmingPool swimmingPool;

    private Thread[] swimmers;

    public SwimmingCentre(int numSwimmers) {
        this.swimmingPool = new SwimmingPool(POOL_CAPACITY);
        this.semaphore = new Semaphore(POOL_CAPACITY);
        this.swimmers = new Thread[numSwimmers];

        for (int swimmerId = 0; swimmerId < numSwimmers; swimmerId++) {
            Swimmer newSwimmer = new Swimmer(swimmerId, semaphore, swimmingPool);
            this.swimmers[swimmerId] = new Thread(newSwimmer);
        }
    }

    public void start() throws InterruptedException {
        for (Thread swimmer: swimmers) {
            swimmer.start();
        }

        for (Thread swimmer: swimmers) {
            swimmer.join();
        }
    }
}

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Swimmer implements Runnable {

    final int MAX_SLEEP = 1000;

    private int id;
    private Random rng;
    private SwimmingPool swimmingPool;

    public Swimmer(int id, SwimmingPool swimmingPool) {
        this.id = id;
        this.swimmingPool = swimmingPool;
        this.rng = new Random();
    }

    private void swim() throws InterruptedException {
        int swimmingTime = rng.nextInt(MAX_SLEEP);

        System.out.println(id + " is about to swim for " + swimmingTime);
        Thread.sleep(swimmingTime);
    }

    @Override
    public void run() {
        try {
            System.out.println(id + " is trying to enter pool.");
            swimmingPool.enter();
            swim();
            System.out.println(id + " exiting pool.");
            swimmingPool.exit();

        } catch (InterruptedException e) {
            System.out.println("Error " + e);
        }
    }
}

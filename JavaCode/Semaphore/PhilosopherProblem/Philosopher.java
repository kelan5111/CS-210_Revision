import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{

    private int Id;

    private Fork rightFork;
    private Fork leftFork;
    private Semaphore semaphore;
    private Random rng;

    public Philosopher(int Id, Fork rightFork, Fork leftFork, Semaphore semaphore) {
        this.Id = Id;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.semaphore = semaphore;
        this.rng = new Random();
    }

    private void eat() throws InterruptedException {
        System.out.println(Id + " is eating.");
        Thread.sleep(rng.nextInt(1000));
        System.out.println(Id + " finished eating.");

        rightFork.release();
        leftFork.release();

        semaphore.release(1);   // released from block once all resources are released
    }

    public void run() {
        while(true) {
            try {
                semaphore.acquire(1);   // enter the block first before acquiring resources

                leftFork.acquire();
                rightFork.acquire();

                eat();

            } catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

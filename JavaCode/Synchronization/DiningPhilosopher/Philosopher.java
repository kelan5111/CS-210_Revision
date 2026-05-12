import java.util.Random;

public class Philosopher implements Runnable{

    private int Id;

    private Waiter waiter;
    private Fork leftFork;
    private Fork rightFork;

    private Random rng;

    public Philosopher(int Id, Fork leftFork, Fork rightFork, Waiter waiter) {
        this.Id = Id;
        this.waiter = waiter;

        this.leftFork = leftFork;
        this.rightFork = rightFork;

        this.rng = new Random();
    }

    private void eat() throws InterruptedException {
        System.out.println(Id + " Started eating...");
        Thread.sleep(rng.nextInt(500));
        System.out.println(Id + " Finished eating...");

        waiter.release(leftFork.getId());
        waiter.release(rightFork.getId());
    }

    public void run() {
        while(true) {

            try {
                waiter.acquire(leftFork.getId());
                waiter.acquire(rightFork.getId());
                eat();

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

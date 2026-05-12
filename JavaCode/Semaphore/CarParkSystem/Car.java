import java.util.Random;
import java.util.concurrent.Semaphore;

public class Car implements Runnable{

    private int Id;
    private Random rng;
    private Semaphore semaphore;
    private Space[] sharedResources;

    public Car(int Id, Space[] sharedResources, Semaphore semaphore) {
        this.Id = Id;
        this.semaphore = semaphore;
        this.sharedResources = sharedResources;
        this.rng = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int randSleep = rng.nextInt(1000);
                Space space = sharedResources[rng.nextInt(sharedResources.length)];

                semaphore.acquire();
                System.out.println("Acquired permit.");
                System.out.println(Id + " is trying to park");
                space.park();
                System.out.println(Id + " has successfully parked in space " + space);
                Thread.sleep(randSleep);
                space.leave();
                semaphore.release();
                System.out.println("Released permit.");
                System.out.println(Id + " has left space " + space);

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

import java.util.Random;

public class Person implements Runnable {

    private final int MAX_SLEEP = 1000;

    private Random rng;
    private Guard guard;

    public Person(Guard guard) {
        this.rng = new Random();
        this.guard = guard;
    }


    @Override
    public void run() {
        while(true) {

            try {
                System.out.println("Person to access vault.");
                guard.enter();
                System.out.println("Person exiting vault.");
                guard.exit();

            } catch(InterruptedException e) {
                System.out.println("Error " + e);
            }
        }
    }
}

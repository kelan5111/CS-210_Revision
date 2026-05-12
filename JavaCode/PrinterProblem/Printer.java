import java.util.Random;

public class Printer {

    final int MAX_PRINTING_TIME= 500;
    final int MIN_PRINTING_TIME = 100;

    private boolean printing;
    private Random rng;

    public Printer(Random rng) {
        this.printing = false;
        this.rng = rng;
    }

    public synchronized void acquire() throws InterruptedException {
        while(printing) {
            System.out.println("Waiting for others to release...");
            wait();
        }

        this.printing = true;
        print();
    }

    public synchronized void release() {
        this.printing = false;
        notifyAll();
    }

    private void print() {
        if (printing) {
            System.out.println("Printing paper...");

            int rand_printing_time = this.rng.nextInt(MIN_PRINTING_TIME, MAX_PRINTING_TIME);

            try {
                Thread.sleep(rand_printing_time);
            } catch(InterruptedException e) {
                System.out.println(e); 
            }

            System.out.println("Finished...");
        }
    }

    // Setters and getters
    public void setPrinting(boolean printing) {
        this.printing = printing;
    }

    public boolean isPrinting() {
        return printing;
    }
}
import java.util.Random;

public class User implements Runnable{

    final int MIN_SLEEP  = 200;
    final int MAX_SLEEP = 1000;

    private int ID;
    private Random rng;
    private Printer printer;
    private ScannerObj scanner; 

    public User(int ID, Random rng, Printer printer, ScannerObj scanner) {
        this.ID = ID;
        this.rng = rng;
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public String toString() {
        return String.format("ID %d: ", ID);
    }

    public void run() {
        int randSleep = this.rng.nextInt(MIN_SLEEP, MAX_SLEEP);

        try {
            System.out.println(this + " trying to acquire printer...");
            this.printer.acquire();
            Thread.sleep(randSleep);

            System.out.println(this + " releasing printer...");
            this.printer.release();

            System.out.print(this + " trying to acquire scanner...");
            this.scanner.acquire();

            System.out.println(this + " releasing scanner...");
            this.scanner.release();

        } catch (InterruptedException e) {
        }
    }
}

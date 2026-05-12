import java.util.Random;

public class ScannerObj {

    final int MIN_SCAN_TIME = 200;
    final int MAX_SCAN_TIME = 500;
    
    private boolean scanning;
    private Random rng;

    public ScannerObj(Random rng) {
        this.scanning = false;
        this.rng = rng;
    }

    public synchronized void acquire() throws InterruptedException{
        while(scanning) {
            wait();
        }

        this.scanning = true;
        scan();
    }

    public synchronized void release() {
        this.scanning = false;
        notifyAll();
    }

    private void scan() {
        int randSleep = rng.nextInt(MIN_SCAN_TIME, MAX_SCAN_TIME);

        try {
            System.out.println("Scanning page...");

            Thread.sleep(randSleep);

            System.out.println("Scanning finished...");

        } catch (InterruptedException e) {
        }
    }

    // Setters and getters
    public boolean isScanning() {
        return scanning;
    }

    public void setScanning(boolean scanning) {
        this.scanning = scanning;
    }
}
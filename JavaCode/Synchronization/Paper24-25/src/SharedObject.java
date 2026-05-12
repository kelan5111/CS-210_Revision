public class SharedObject {

    private int maxCount;
    private int count;

    public SharedObject(int maxCount) {
        this.maxCount = maxCount;
        start();
    }

    public synchronized void start() {
        this.count = 0;
    }

    public synchronized void event() {
        this.count += 1;
        notifyAll();
    }

    public synchronized int tally() throws InterruptedException{
        while (count == 0) {
            wait();
        }
        return count;
    }
}

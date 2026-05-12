public class SharedObject {

    private int counter;

    public SharedObject() {
        this.counter = 0;
    }

    public synchronized void start() {
        this.counter = 0;
    }

    public synchronized void event() {
        counter += 1;
        notifyAll();
    }

    public synchronized int tally() throws InterruptedException{
        while (counter == 0) {
            wait();
        }
        return counter;
    }
}
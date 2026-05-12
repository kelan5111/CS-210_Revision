public class Fork {

    private int Id;
    private boolean acquired;

    public Fork(int forkId) {
        this.Id = forkId;
        this.acquired = false;
    }

    public synchronized void acquire() throws InterruptedException {
        while(acquired) {
            wait();
        }
        this.acquired = true;
    }

    public synchronized void release() {
        acquired = false;
        notifyAll();
    }

    // Setters and getters
    public int getId() {
        return Id;
    }

    public boolean isAcquired() {
        return acquired;
    }
}

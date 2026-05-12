/* Monitor class */

public class Waiter {

    private Fork[] sharedResource;

    public Waiter(Fork[] forks) {
        this.sharedResource = forks;
    }

    public synchronized void acquire(int index) throws InterruptedException{
        while(sharedResource[index].isAcquired()) {
            wait();
        }
        this.sharedResource[index].setAcquired(true);
    }

    public synchronized void release(int index) {
        this.sharedResource[index].setAcquired(false);
        notifyAll();
    }
}

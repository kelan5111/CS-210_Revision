public class ResourcePool {
    
    private int poolCapacity;
    private int maxCapacity;

    public ResourcePool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.poolCapacity = 5;
    }

    public synchronized void acquire() throws InterruptedException {
        while(poolCapacity == 0) {
            wait();
        }
        poolCapacity -= 1;
        notifyAll();
    }

    public synchronized void release() throws InterruptedException {
        while(poolCapacity == maxCapacity) {
            wait();
        }
        poolCapacity += 1;
        notifyAll();
    }

    public synchronized int available() {
        return poolCapacity;
    }
}

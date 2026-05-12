public class WaterDispenser {
    
    private int maxCapacity;
    private int capacity;

    public WaterDispenser(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.capacity = 0;
    }

    public synchronized void refill() {
        capacity = maxCapacity;
        notifyAll();
    }

    public synchronized void serve() throws InterruptedException{
        while (capacity == 0) { 
            wait();
        }
        capacity -= 1;
        notifyAll();
    }

    public synchronized int cupsLeft() {
        return capacity;
    }
}

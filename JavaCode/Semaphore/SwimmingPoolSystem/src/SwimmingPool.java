public class SwimmingPool {

    private int capcity;
    private int maxCapacity;

    public SwimmingPool(int maxCapacity) {
        this.capcity = 0;
        this.maxCapacity = maxCapacity;
    }

    public synchronized void enter() throws InterruptedException {
        while (capcity == maxCapacity) {
            wait();
        }
        increment();
    }

    public synchronized void exit() {
        decrement();
        notifyAll();
    }

    private void increment() {
        this.capcity++;
    }

    private void decrement() {
        this.capcity--;
    }
}

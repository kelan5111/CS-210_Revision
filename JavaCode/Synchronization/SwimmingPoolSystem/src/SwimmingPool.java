public class SwimmingPool {

    final int MAX_CAPACITY = 3;

    private int capacity;

    public SwimmingPool() {
        this.capacity = 0;
    }

    //Setters and getters

    public void increment() {
        this.capacity++;
    }

    public void decrement() {
        this.capacity--;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == MAX_CAPACITY;
    }
}

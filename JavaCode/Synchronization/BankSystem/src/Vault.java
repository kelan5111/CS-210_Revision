public class Vault {

    final int MAX_CAPACITY = 2;

    private int capacity;
    private boolean open;

    public Vault() {
        this.open = true;
    }


    // Setters and getters

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}

public class Fork {

    private int Id;
    private boolean acquired;

    public Fork(int Id) {
        this.Id = Id;
        this.acquired = false;
    }

    // Setters and getters
    public boolean isAcquired() {
        return acquired;
    }

    public void setAcquired(boolean acquired) {
        this.acquired = acquired;
    }

    public int getId() {
        return Id;
    }
}
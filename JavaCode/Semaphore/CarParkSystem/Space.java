public class Space {

    private int Id;
    private boolean taken;

    public Space(int Id) {
        this.taken = false;
        this.Id = Id;
    }

    @Override
    public String toString() {
        return String.format("Space %d", Id);
    }

    public synchronized void park() throws InterruptedException{
        while (taken) {
            wait();
        }
        this.taken = true;
    }

    public synchronized void leave() {
        this.taken = false;
        notifyAll();
    }

    // Setters and getters
    public boolean isTaken() {
        return taken;
    }
}

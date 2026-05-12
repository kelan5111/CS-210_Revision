public class Space {

    private int Id;
    private boolean taken;

    public Space(int Id) {
        this.taken = false;
        this.Id = Id;
    }

    // Setters and getters
    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}

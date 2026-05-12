public class Doctor {
    private int Id;
    private boolean treating;

    public Doctor(int Id) {
        this.Id = Id;
        this.treating = false;
    }

    public synchronized boolean request() throws InterruptedException{
        if (treating) {
            return false;
        }
        treat();
        return true;
    }

    public synchronized void treat() {
        System.out.println("Doctor " + Id + " is now treating.");
        this.treating = true;
    }

    public synchronized void finish() {
        this.treating = false;
        notifyAll();
    }

    // Setters and getters


    public boolean isTreating() {
        return treating;
    }

    public void setTreating(boolean treating) {
        this.treating = treating;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

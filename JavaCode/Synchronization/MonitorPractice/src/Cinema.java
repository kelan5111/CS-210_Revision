public class Cinema {

    private int maxPeople;
    private int people;

    public Cinema(int maxPeople) {
        this.maxPeople = maxPeople;
        this.people = 0;
    }

    public synchronized void enter() throws InterruptedException {
        while (people == maxPeople) {
            wait();
        }
        this.people++;
        notifyAll();
    }

    public synchronized void exit() throws InterruptedException {
        while(people == 0) {
            wait();
        }
        this.people--;
        notifyAll();
    }

    public synchronized int capacity() {
        return people;
    }
}

public class Printer {

    private int maxJobs;
    private int currentJobs;

    public Printer(int maxJobs) {
        this.maxJobs = maxJobs;
        this.currentJobs = 0;
    }

    public synchronized void addJob() throws InterruptedException {
        while(currentJobs == maxJobs) {
            wait();
        }
        this.currentJobs++;
        notifyAll();
    }

    public synchronized void processJob() throws InterruptedException {
        while (currentJobs == 0) {
            wait();
        }
        this.currentJobs--;
        notifyAll();
    }

    public synchronized int jobCount() {
        return currentJobs;
    }
}

public class SwimmingCentre {

    private SwimmingPool swimmingPool;
    private Thread[] swimmers;

    public SwimmingCentre(int numSwimmers) {
        this.swimmingPool = new SwimmingPool();
        this.swimmers = new Thread[numSwimmers];

        for (int swimmerId = 0; swimmerId < numSwimmers; swimmerId++) {
            Swimmer newSwimmer = new Swimmer(swimmerId, this);

            this.swimmers[swimmerId] = new Thread(newSwimmer);
        }
    }

    public synchronized void enterPool() throws InterruptedException {
        while (swimmingPool.isFull()) {
            wait();
        }
        swimmingPool.increment();
    }

    public synchronized void exitPool() {
        swimmingPool.decrement();
        notifyAll();
    }

    public void start() throws InterruptedException {
        for (Thread swimmer: swimmers) {
            swimmer.start();
        }

        for (Thread swimmer: swimmers) {
            swimmer.join();
        }
    }
}

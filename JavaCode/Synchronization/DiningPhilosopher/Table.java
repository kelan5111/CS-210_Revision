public class Table {

    private Thread[] philosophers;
    private Fork[] forks;
    private Waiter waiter;

    public Table(int totalPhilosopher) {

        this.philosophers = new Thread[totalPhilosopher];
        this.forks = new Fork[totalPhilosopher];

        for (int forkId = 0; forkId < totalPhilosopher; forkId++) {
            forks[forkId] = new Fork(forkId);
        }

        this.waiter = new Waiter(forks);

        for (int philId = 0; philId < totalPhilosopher; philId++) {
            if (philId == totalPhilosopher - 1) {
                Philosopher phil = new Philosopher(philId, forks[philId], forks[0], waiter);
                philosophers[philId] = new Thread(phil);
            } else {
                Philosopher phil = new Philosopher(philId, forks[philId], forks[philId + 1], waiter);
                philosophers[philId] = new Thread(phil);
            }
        }
    }

    public void start() throws InterruptedException {

        for(Thread philThread: philosophers) {
            philThread.start();
        }

        for(Thread philThread: philosophers) {
            philThread.join();
        }
    }
}

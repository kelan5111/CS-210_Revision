import java.util.concurrent.Semaphore;

public class Table {
    private Semaphore semaphore;
    private Fork[] sharedResource;
    private Thread[] phils;

    public Table(int NumPhil) {
        this.semaphore = new Semaphore(NumPhil - 1);
        this.sharedResource = new Fork[NumPhil];
        this.phils = new Thread[NumPhil];

        for(int forkId = 0; forkId < NumPhil; forkId++) {
            sharedResource[forkId] = new Fork(forkId);
        }

        for(int philId = 0; philId < NumPhil; philId++) {
            if (philId == NumPhil - 1) {
                Philosopher phil = new Philosopher(
                        philId,
                        sharedResource[philId],
                        sharedResource[0],
                        semaphore
                );
                phils[philId] = new Thread(phil);
            } else {
                Philosopher phil = new Philosopher(
                        philId,
                        sharedResource[philId],
                        sharedResource[philId+1],
                        semaphore
                );
                phils[philId] = new Thread(phil);
            }
        }
    }

    public void start() throws InterruptedException{
        for(Thread philThread: phils) {
            philThread.start();
        }

        for(Thread philThread: phils) {
            philThread.join();
        }
    }
}

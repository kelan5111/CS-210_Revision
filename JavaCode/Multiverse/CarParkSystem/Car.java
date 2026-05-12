import org.multiverse.api.references.TxnInteger;

public class Car implements Runnable {

    private int id;
    private Barrier sharedBarrier;
    private boolean complete;

    public Car(int id, Barrier sharedBarrier) {
        this.sharedBarrier = sharedBarrier;
        this.id = id;

        this.complete = false;
    }

    @Override
    public void run() {
        try {
            System.out.println(id + " trying to enter barrier...");
            sharedBarrier.enter();
            sharedBarrier.exit();
            System.out.println(id + " has exited the barrier.");

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

import org.multiverse.api.StmUtils;
import org.multiverse.api.references.*;

public class SwimmingPool {

    private TxnInteger capacity;
    private int maxCapacity;

    public SwimmingPool(int maxCapacity) {
        this.capacity = StmUtils.newTxnInteger(0);
        this.maxCapacity = maxCapacity;
    }

    public void enter() {
        StmUtils.atomic(()->{
            if (capacity.get() == maxCapacity) {
                StmUtils.retry();
            }
            this.capacity.increment();
        });
    }

    public void exit() {
        StmUtils.atomic(()->{
            if (capacity.get() == 0) {
                StmUtils.retry();
            }
            this.capacity.decrement();
        });
    }

    //Setters and getters

    public int getCapacity() {
        return capacity.atomicGet();
    }
}

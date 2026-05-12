import org.multiverse.api.StmUtils;
import org.multiverse.api.references.*;

public class Pot {
    private TxnInteger servingCount;
    private TxnInteger capacity;

    public Pot(int capacity) {
        this.capacity = StmUtils.newTxnInteger(capacity);
        this.servingCount = StmUtils.newTxnInteger(capacity);
    }

    public void take() {
        StmUtils.atomic(() -> {
            if (servingCount.get() > 0) {
                int newServingCount = servingCount.get() - 1;
                servingCount.set(newServingCount);
            }
        });
    }

    public void refill() {
        StmUtils.atomic(() -> {
            if (servingCount.get() == 0) {
                int newServingCount = capacity.get();
                servingCount.set(newServingCount);
            }
        });
    }
}

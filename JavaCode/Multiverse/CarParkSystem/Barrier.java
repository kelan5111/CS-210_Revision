import static org.multiverse.api.StmUtils.*;
import org.multiverse.api.references.*;

import java.util.Random;

public class Barrier {

    private final TxnInteger spaces;
    private int spaceCapacity;

    public Barrier(int spaceCapacity) {
        this.spaceCapacity = spaceCapacity;

        this.spaces = newTxnInteger(spaceCapacity);
    }

    public synchronized void enter() throws InterruptedException {
        atomic(() -> {
            if (spaces.get() > 0) {
                spaces.set(spaces.get() - 1);
            } else {
                retry();
            }
        });
    }

    public synchronized void exit() throws InterruptedException{
        atomic(() -> {
            if (spaces.get() < spaceCapacity) {
                spaces.set(spaces.get() + 1);
            } else {
                retry();
            }
        });
    }

    // Setters and getters

    public void setSpaces(int spaces) {
        this.spaces.atomicSet(spaces);
    }

    public int getSpaces() {
        return spaces.atomicGet();
    }
}

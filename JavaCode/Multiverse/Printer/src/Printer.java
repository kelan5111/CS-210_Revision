import org.multiverse.api.StmUtils;
import org.multiverse.api.references.*;

public class Printer {

    private TxnInteger tasks;

    public Printer(int maxTasks) {
        this.tasks = StmUtils.newTxnInteger(maxTasks);
    }

    public void addTask() {
        StmUtils.atomic(() -> {
            tasks.set(tasks.get()+1);
        });
    }

    public void runTask() {
        StmUtils.atomic(() -> {
            tasks.set(tasks.get()-1);
        });
    }

    // Setters and getters
    public int getTasks() {
        return tasks.atomicGet();
    }

    public void setTasks(int tasks) {
        this.tasks.atomicSet(tasks);
    }
}



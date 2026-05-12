import static org.multiverse.api.StmUtils.*;
import org.multiverse.api.references.*;

 public class Account {
    private TxnLong balance;

    public Account(long initial) {
        this.balance = newTxnLong(initial);
    }

     public long getBalance() {
         return balance.atomicGet();
     }

     public void deposit(long amount) {
        atomic(() -> {
            long newBalance = balance.get() + amount;
            this.balance.set(newBalance);
        });
     }

     public void withdraw(long amount) {
         atomic(() -> {
             long newBalance = balance.get() - amount;
             this.balance.set(newBalance);
         });
     }

     public static void transfer(Account from, Account to, long amount) {
        atomic(() -> {
            if (from.balance.get() < amount) {
                throw new IllegalStateException("insufficient funds");
            }

            from.withdraw(amount);
            to.deposit(amount);
        });
     }
 }
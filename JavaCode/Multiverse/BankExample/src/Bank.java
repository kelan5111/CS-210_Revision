import org.multiverse.api.references.*;
import java.util.Random;

public class Bank {
    private final int MAX_TELLERS = 5;
    private final int MAX_ACCOUNTS = 10;

    private Account[] accounts;
    private Thread[] tellers;

    public Bank() {
        this.accounts = new Account[MAX_ACCOUNTS];
        this.tellers = new Thread[MAX_TELLERS];

        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            accounts[i] = new Account(1000);
        }

        for (int i = 0; i < MAX_TELLERS; i++) {
            Teller newTeller = new Teller(accounts);
            tellers[i] = new Thread(newTeller);
        }
    }

    public void printSummary() {
        for (Account account: accounts) {
            System.out.println(account.getBalance());
        }
    }

    public void start() throws InterruptedException {
        long startingTotal = totalMoney();

        for (Thread teller : tellers) {
            teller.start();
        }

        Thread.sleep(5000);

        for (Thread teller : tellers) teller.interrupt();
        for (Thread teller : tellers) teller.join();

        long endingTotal = totalMoney();

        System.out.println("Starting total: " + startingTotal);
        System.out.println("Ending total:   " + endingTotal);
        System.out.println("Difference:     " + (endingTotal - startingTotal));
    }

    private long totalMoney() {
        long total = 0;
        for (Account a : accounts) total += a.getBalance();
        return total;
    }
}

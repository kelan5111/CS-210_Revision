import java.util.Random;

public class Teller implements Runnable {

   private Account[] accounts;
   private Random rng;

   public Teller(Account[] account) {
       this.accounts = account;
       this.rng = new Random();
   }

    @Override
    public void run() {
        while (true) {
            Account sender = accounts[rng.nextInt(accounts.length)];
            Account recipient = accounts[rng.nextInt(accounts.length)];
            long randAmount = rng.nextLong(100);

            try {
                Thread.sleep(rng.nextInt(50));

                if (sender != recipient) {
                    try {
                        Account.transfer(sender, recipient, randAmount);
                    } catch (IllegalStateException ex) {
                        System.out.println("Sender has insufficient funds.");
                    }
                }

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

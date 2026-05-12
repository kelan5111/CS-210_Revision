import java.util.Random;

public class PrintingManager {

    final int MAX_USERS = 3;

    Thread[] users;
    Printer printer;
    ScannerObj scanner;


    public PrintingManager() {
        this.printer = new Printer(new Random());
        this.scanner = new ScannerObj(new Random());
        this.users = new Thread[MAX_USERS];

        for (int userID = 0; userID < MAX_USERS; userID++) {
            User newUser = new User(userID, new Random(), printer, scanner);
            users[userID] = new Thread(newUser);
        }
    }

    public void start() throws InterruptedException {
        for (Thread userThread: users) {
            userThread.start();
        }

        for (Thread userThread: users) {
            userThread.join();
        }
    }
}

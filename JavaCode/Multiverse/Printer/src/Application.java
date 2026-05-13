public class Application {

    static final int MAX_USERS = 3;
    static final int MAX_TASKS = 2;

    static Thread[] users;

    static public void main(String[] args) throws InterruptedException {
        Printer printer = new Printer(MAX_TASKS);
        users = new Thread[MAX_USERS];

        for (int userId = 0; userId < MAX_USERS; userId++) {
            User newUser = new User(userId, printer);
            Thread threadUser = new Thread(newUser);

            users[userId] = threadUser;
        }


        for (Thread userThread: users) {
            userThread.start();
        }

        for (Thread userThread: users) {
            userThread.join();
        }
    }
}

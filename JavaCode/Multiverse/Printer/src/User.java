public class User implements Runnable {

    private int id;
    private Printer sharedPrinter;

    public User(int id, Printer sharedPrinter) {
        this.id = id;
        this.sharedPrinter = sharedPrinter;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(id + " trying to access printer.");
            sharedPrinter.addTask();
            sharedPrinter.runTask();
            System.out.println(id + " printing complete.");
        }
    }
}

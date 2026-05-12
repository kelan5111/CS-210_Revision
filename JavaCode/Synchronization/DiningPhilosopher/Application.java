public class Application {

    final int TOTAL_PHIL = 5;

    public void main(String[] args) throws InterruptedException {
        Table table = new Table(TOTAL_PHIL);

        table.start();
    }
}

import java.util.Random;

public class Swimmer implements Runnable {

    final int MAX_SLEEP = 1000;

    private int id;
    private SwimmingCentre swimmingCentre;
    private Random rng;

    public Swimmer(int id, SwimmingCentre swimmingCentre) {
        this.swimmingCentre = swimmingCentre;
        this.id = id;
        this.rng = new Random();
    }

    public void swim() throws InterruptedException {
        int swimTime = rng.nextInt(MAX_SLEEP);

        System.out.println(id + " swimming for " + swimTime + " amount of time.");
        Thread.sleep(swimTime);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(id + " trying to access pool.");
                swimmingCentre.enterPool();
                swim();
                System.out.println(id + " has finished swimming, exiting pool.");
                swimmingCentre.exitPool();

            } catch (InterruptedException e) {
                System.out.println("Error " + e);
            }
        }
    }
}

import java.util.Random;

public class Car implements Runnable{

    private int Id;
    private Random rng;
    private CarPark carPark;

    public Car(int Id, CarPark carPark) {
        this.Id = Id;
        this.carPark = carPark;
        this.rng = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int randSleep = rng.nextInt(1000);
                int randSpace = rng.nextInt(carPark.MAX_SPACES);

                System.out.println(Id + " is trying to park");
                carPark.park(randSpace);
                System.out.println(Id + " has successfully parked in space " + randSpace);
                Thread.sleep(randSleep);
                carPark.leave(randSpace);
                System.out.println(Id + " has left space " + randSpace);

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

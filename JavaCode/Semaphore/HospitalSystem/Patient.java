import javax.print.Doc;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Patient implements Runnable{
    private int Id;
    private Random rng;

    private Doctor[] doctors;
    private Doctor personalDoctor;

    private Semaphore semaphore;

    public Patient(int Id, Semaphore semaphore, Doctor[] doctors) {
        this.Id = Id;
        this.semaphore = semaphore;
        this.doctors = doctors;

        this.personalDoctor = null;
        this.rng = new Random();
    }

    @Override
    public void run() {
        try {
            int randSleep = rng.nextInt(1000);
            semaphore.acquire(1);

            while (true) {
                personalDoctor = doctors[rng.nextInt(doctors.length)];

                if (personalDoctor.request()) {
                    break;
                }
            }
            System.out.println(Id + " being treated by Doctor " + personalDoctor.getId());
            Thread.sleep(randSleep);
            System.out.println(Id + " treated.");
            personalDoctor.finish();
            semaphore.release(1);

        } catch (InterruptedException e) {
                System.out.println(e);
        }
    }

    // Getters and setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

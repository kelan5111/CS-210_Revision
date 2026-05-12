import java.util.concurrent.Semaphore;

public class CarPark {

    private Thread[] carThreads;
    private Semaphore semaphore;
    private Space[] sharedResource;

    public CarPark(int maxCars, int maxSpace) {
        this.semaphore = new Semaphore(maxSpace);

        this.carThreads = new Thread[maxCars];
        this.sharedResource = new Space[maxSpace];

        for (int spaceId = 0; spaceId < maxSpace; spaceId++) {
            sharedResource[spaceId] = new Space(spaceId);
        }

        for(int carId = 0; carId < maxCars; carId++) {
            Car car = new Car(carId, sharedResource, semaphore);
            Thread carThread = new Thread(car);

            carThreads[carId] = carThread;
        }
    }

    public void start() throws InterruptedException {
        System.out.println("Starting CarPark...\n");

        for (Thread carThread: carThreads) {
            carThread.start();
        }

        for (Thread carThread: carThreads) {
            carThread.join();
        }
    }
}

public class CarPark {

    final int MAX_SPACES = 5;
    static boolean open = false;

    private int maxCars;

    private Thread[] cars;
    private Barrier barrier;

    public CarPark(int maxCars) {
        this.maxCars = maxCars;
        this.cars = new Thread[maxCars];
        this.barrier = new Barrier(MAX_SPACES);

        for (int carId = 0; carId < maxCars; carId++) {
            Car newCar = new Car(carId, barrier);
            Thread carThread = new Thread(newCar);

            cars[carId] = carThread;
        }
    }

    public void start() throws InterruptedException {
        if (isOpen()) {
            System.out.println("Car park open.\n");
            for (Thread carThread : cars) {
                carThread.start();
            }

            for (Thread carThread : cars) {
                carThread.join();
            }
        } else if (!open) {
            System.out.println("Car park closed.");
        }
        System.out.println("Empty car park.");
    }

    public boolean isOpen() {
        return open;
    }

    public static void setOpen(boolean status) {
        open = status;
    }
}

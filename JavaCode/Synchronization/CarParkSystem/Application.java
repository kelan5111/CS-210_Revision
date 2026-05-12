public class Application {

    final static int MAX_CARS = 5;

    static void main(String[] args) throws InterruptedException {
        CarPark carPark = new CarPark();
        Thread[] carThreads = new Thread[MAX_CARS];

        for(int carId = 0; carId < MAX_CARS; carId++) {
            Car car = new Car(carId, carPark);
            Thread carThread = new Thread(car);

            carThreads[carId] = carThread;
        }

        for (Thread carThread: carThreads) {
            carThread.start();
        }

        for (Thread carThread: carThreads) {
            carThread.join();
        }
    }
}

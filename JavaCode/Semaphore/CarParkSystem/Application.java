public class Application {

    static void main(String[] args) throws InterruptedException {
        CarPark carPark = new CarPark(3, 6);

        carPark.start();
    }
}

public class Application {

    static final int MAX_CARS = 10;

    public static void main(String[] args) throws InterruptedException {
        CarPark carPark = new CarPark(MAX_CARS);
        CarPark.setOpen(true);
        
        carPark.start();
    }
}

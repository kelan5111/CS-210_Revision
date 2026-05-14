public class Application {

    static public void main(String[] args) throws InterruptedException {
        int numSwimmers = 5;
        SwimmingCentre swimmingCentre = new SwimmingCentre(numSwimmers);

        swimmingCentre.start();
    }
}

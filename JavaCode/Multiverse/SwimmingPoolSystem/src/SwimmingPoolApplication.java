public class SwimmingPoolApplication {

    static public void main(String[] args) throws InterruptedException {
        int numSwimmers = 20;
        SwimmingCentre swimmingCentre = new SwimmingCentre(numSwimmers);

        swimmingCentre.start();
    }
}

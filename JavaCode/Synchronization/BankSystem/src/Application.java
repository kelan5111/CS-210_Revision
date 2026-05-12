public class Application {


    static public void main(String[] args) throws InterruptedException{

        Bank bank = new Bank(4);

        bank.start();
    }
}

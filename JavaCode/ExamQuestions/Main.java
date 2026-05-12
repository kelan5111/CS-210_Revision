public class Main {
    
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();

        sharedObject.start();
        sharedObject.event();
        
        System.out.println(sharedObject.tally());
    }
}

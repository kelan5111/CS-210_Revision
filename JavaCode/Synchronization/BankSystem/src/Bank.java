public class Bank {

    private Thread[] people;
    private Vault vault;
    private Guard guard;


    public Bank(int totalPeople) {
        this.people = new Thread[totalPeople];
        this.vault = new Vault();
        this.guard = new Guard(vault);

        for (int i=0; i < totalPeople; i++) {
            Person newPerson = new Person(guard);
            Thread personThread = new Thread(newPerson);

            this.people[i] = personThread;
        }
    }

    public void start() throws InterruptedException {

        for (Thread threadPerson: people) {
            threadPerson.start();
        }

        for (Thread threadPerson: people) {
            threadPerson.join();
        }
    }
}

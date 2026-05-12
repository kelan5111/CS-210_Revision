public class Guard {

    private Vault vault;

    public Guard(Vault vault) {
        this.vault = vault;
    }

    public synchronized void enter() throws InterruptedException {
        if (vault.isOpen()) {
            while(vault.getCapacity() == vault.MAX_CAPACITY) {
                wait();
            }
            vault.setCapacity(vault.getCapacity() + 1);
        }
    }

    public synchronized void exit() throws InterruptedException{
        while(vault.getCapacity() == 0) {
            wait();
        }
        vault.setCapacity(vault.getCapacity() - 1);
        notifyAll();
    }
}

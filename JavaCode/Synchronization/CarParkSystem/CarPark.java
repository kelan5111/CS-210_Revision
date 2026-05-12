/* Monitor class */

public class CarPark {

    final int MAX_SPACES = 3;
    private Space[] sharedResource;

    public CarPark() {
        this.sharedResource = new Space[MAX_SPACES];

        // Creating shared resources
        for (int spaceId = 0; spaceId < MAX_SPACES; spaceId++) {
            this.sharedResource[spaceId] = new Space(spaceId);
        }
    }

    public synchronized void park(int spaceId) throws InterruptedException{
        while (sharedResource[spaceId].isTaken()) {
            wait();
        }
        sharedResource[spaceId].setTaken(true);
    }

    public synchronized void leave(int spaceId) {
        sharedResource[spaceId].setTaken(false);
        notifyAll();
    }
}

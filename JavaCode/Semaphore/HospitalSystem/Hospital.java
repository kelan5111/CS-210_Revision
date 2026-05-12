import java.util.concurrent.Semaphore;

public class Hospital {

    final int MAX_DOCTORS = 2;
    final int MAX_PATIENTS = 20;

    private Doctor[] sharedResource;
    private Thread[] patients;
    private Semaphore sharedSemaphore;

    public Hospital() {
        this.sharedSemaphore = new Semaphore(MAX_DOCTORS);
        this.sharedResource = new Doctor[MAX_DOCTORS];
        this.patients = new Thread[MAX_PATIENTS];

        for (int doctorId = 0; doctorId < MAX_DOCTORS; doctorId++) {
            Doctor newDoctor = new Doctor(doctorId);

            sharedResource[doctorId] = newDoctor;
        }

        for (int patientId = 0; patientId < MAX_PATIENTS; patientId++) {
            Patient newPatient = new Patient(patientId, sharedSemaphore, sharedResource);
            Thread patientThread = new Thread(newPatient);

            patients[patientId] = patientThread;
        }
    }

    public void start() throws InterruptedException {
        for (Thread patientThread: patients) {
            patientThread.start();
        }

        for (Thread patientThread: patients) {
            patientThread.join();
        }
    }
}


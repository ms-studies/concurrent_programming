package zad4;

import java.util.concurrent.Semaphore;

/**
 * Created by Marcin Szalek on 13.11.17.
 */
public class Bankier {

    private final Semaphore semaphore;

    public Bankier(int zasoby) {
        semaphore = new Semaphore(zasoby, false);
    }

    public int pozycz(int ile) {
        semaphore.acquireUninterruptibly(ile);
        System.out.println("[BANKIER]: Pożyczam " + ile + ". Zostało mi: " + semaphore.availablePermits());
        return ile;
    }

    public void oddaj(int ile) {
        semaphore.release(ile);
        System.out.println("[BANKIER]: Odebrałem " + ile + ". Zostało mi: " + semaphore.availablePermits());
    }
}

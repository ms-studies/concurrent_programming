package zad3;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class ProcesWylaczny extends Thread {

    public final int NUMER_ZASOBU;
    private final SynchronousQueue<Integer> synchronousQueue;

    public ProcesWylaczny(int numer_zasobu, SynchronousQueue<Integer> synchronousQueue) {
        NUMER_ZASOBU = numer_zasobu;
        this.synchronousQueue = synchronousQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Proces wylaczny nr " + NUMER_ZASOBU + " prosi o zasób " + NUMER_ZASOBU);
                Integer nrZasobu = null;
                while (nrZasobu == null) {
                    nrZasobu = synchronousQueue.poll();
                }
                // System.out.println("Proces wylaczny nr " + NUMER_ZASOBU + " korzysta z zasobu " + nrZasobu);
                //System.out.println("Zasob nr " + nrZasobu + " korzystany przez watek wylaczny nr" + NUMER_ZASOBU);
                sleep(new Random().nextInt(2000));
                // System.out.println("Proces wylaczny nr " + NUMER_ZASOBU + " chce oddać zasób " + NUMER_ZASOBU);
                synchronousQueue.put(nrZasobu);
                System.out.println("Proces wylaczny nr " + NUMER_ZASOBU + " oddał zasób " + NUMER_ZASOBU);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

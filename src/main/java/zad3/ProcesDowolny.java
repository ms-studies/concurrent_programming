package zad3;

import java.util.List;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class ProcesDowolny extends Thread {

    public final int NUMER;
    private final List<SynchronousQueue<Integer>> synchronousQueues;

    public ProcesDowolny(int numer, List<SynchronousQueue<Integer>> synchronousQueues) {
        NUMER = numer;
        this.synchronousQueues = synchronousQueues;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Integer nr_zasobu = new Random().nextInt(synchronousQueues.size());
                System.out.println("Proces dowolny nr " + NUMER + " prosi o zasób " + nr_zasobu);
                Integer zasob = null;
                while (zasob == null) {
                    zasob = synchronousQueues.get(nr_zasobu).poll();
                }
                //System.out.println("Proces dowolny nr " + NUMER + " korzysta z zasobu " + zasob);
                //System.out.println("Zasob nr " + nrZasobu + " korzystany przez watek wylaczny nr" + NUMER_ZASOBU);
                sleep(new Random().nextInt(2000));
                //System.out.println("Proces dowolny nr " + NUMER + " chce oddać zasób " + zasob);
                synchronousQueues.get(nr_zasobu).put(zasob);
                System.out.println("Proces dowolny nr " + NUMER + " oddał zasób " + zasob);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

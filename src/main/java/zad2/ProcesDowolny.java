package zad2;

import java.util.Random;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class ProcesDowolny extends Thread {
    public final int NUMER;
    private final Monitor monitor;

    public ProcesDowolny(int numer, Monitor monitor) {
        NUMER = numer;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int nrZasobu = monitor.korzystaj(this);
                System.out.println("Proces dowolny nr " + NUMER + " korzysta z zasobu " + nrZasobu);
                //System.out.println("Zasob nr " + nrZasobu + " korzystany przez watek dowolny nr" + NUMER);
                sleep(new Random().nextInt(2000));
                monitor.zwolnij(nrZasobu);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

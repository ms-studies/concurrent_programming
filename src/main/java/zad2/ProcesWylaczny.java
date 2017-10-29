package zad2;

import java.util.Random;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class ProcesWylaczny extends Thread {

    public final int NUMER_ZASOBU;
    private final Monitor monitor;

    public ProcesWylaczny(int numer_zasobu, Monitor monitor) {
        NUMER_ZASOBU = numer_zasobu;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int nrZasobu = monitor.korzystaj(this);
                System.out.println("Proces wylaczny nr " + NUMER_ZASOBU + " korzysta z zasobu " + nrZasobu);
                //System.out.println("Zasob nr " + nrZasobu + " korzystany przez watek wylaczny nr" + NUMER_ZASOBU);
                sleep(new Random().nextInt(2000));
                monitor.zwolnij(nrZasobu);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

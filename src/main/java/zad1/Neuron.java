package zad1;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by Marcin Szalek on 30.10.17.
 */
public class Neuron extends Thread {
    private final int NUMER;
    public double wartosc;
    public final Semaphore semaphore = new Semaphore(2);
    public Neuron n1;
    public Neuron n2;

    public Neuron(int numer) {
        NUMER = numer;
        wartosc = numer;
    }

    @Override
    public void run() {
        System.out.println("semafor "+NUMER);
        licz(n1, n2);
    }

    public void licz(Neuron n1, Neuron n2) {
        for (int i = 0; i < 10; i++) {
            //System.out.println("---chce liczyc "+NUMER);
            semaphore.acquireUninterruptibly(2);
            double wartosc1 = n1.wartosc;
            n1.semaphore.release();
            double wartosc2 = n2.wartosc;
            n2.semaphore.release();
            wartosc = wartosc1 + wartosc2;
            System.out.println(NUMER);

            try {
                sleep(new Random().nextInt(1000)+200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(wartosc);
        }
    }
}

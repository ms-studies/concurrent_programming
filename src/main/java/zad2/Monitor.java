package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class Monitor {
    private final int POJEMNOSC;

    private int[] zasoby;

    public Monitor(int pojemnosc) {
        POJEMNOSC = pojemnosc;
        zasoby = new int[pojemnosc];
    }

    //zwraca numer zasobu
    public synchronized int korzystaj(Thread thread) throws InterruptedException {
        int nr_zasobu = 0;
        if (thread instanceof ProcesDowolny) {
            //System.out.println("dow" + thread);
            nr_zasobu = new Random().nextInt(POJEMNOSC);
        } else if (thread instanceof ProcesWylaczny) {
            //System.out.println("wyl" + thread);
            nr_zasobu = ((ProcesWylaczny)thread).NUMER_ZASOBU;
        }
        while (!zasobWolny(nr_zasobu)){
            wait();
        }
        zasoby[nr_zasobu]++;
        //System.out.println("Zasob "+nr_zasobu + " zajety");
        return nr_zasobu;
    }

    public synchronized void zwolnij(int nr_zasobu) throws InterruptedException {
        zasoby[nr_zasobu]--;
        //System.out.println("Zasob "+nr_zasobu + " zwolniony");
        notifyAll();
    }

    private boolean zasobWolny(int nr_zasobu) {
        return zasoby[nr_zasobu]==0;
    }
}

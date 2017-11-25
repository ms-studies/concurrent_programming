package zad3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class Main {

    public static void main(String[] args) {
        int n = 5;
        List<Thread> procesy = new ArrayList<>();
        List<SynchronousQueue<Integer>> synchronousQueues = new ArrayList<>();
//        Monitor monitor = new Monitor(n);
        for (int i = 0; i < n; i++) {
            SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
            synchronousQueues.add(queue);
            ProcesPrzyjmujacy procesPrzyjmujacy = new ProcesPrzyjmujacy(i, queue);
            procesPrzyjmujacy.start();
        }
        for (int i = 0; i < n; i++) {
            procesy.add(new ProcesDowolny(i, synchronousQueues));
            procesy.add(new ProcesWylaczny(i, synchronousQueues.get(i)));
        }
        procesy.add(new ProcesDowolny(n, synchronousQueues));
        procesy.forEach(proces -> proces.start());
    }
}

package zad2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Szalek on 29.10.17.
 */
public class Main {

    public static void main(String[] args) {
        int n = 10;
        List<Thread> procesy = new ArrayList<>();
        Monitor monitor = new Monitor(n);
        for (int i = 0; i < n; i++) {
            procesy.add(new ProcesDowolny(i, monitor));
            procesy.add(new ProcesWylaczny(i, monitor));
        }
        procesy.add(new ProcesDowolny(n, monitor));
        procesy.forEach(proces -> proces.start());
    }
}

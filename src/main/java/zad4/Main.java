package zad4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Szalek on 13.11.17.
 */
public class Main {

    public static void main(String[] args) {
        int ileZasobow = 10;
        int ileKlientow = 10;
        Bankier bankier = new Bankier(ileZasobow);
        System.out.println("Bankier ma " + ileZasobow + " zasobów");
        System.out.println("Klienci: ");
        List<Klient> klienci = new ArrayList<>();
        for (int i = 0; i < ileKlientow; i++) {
            klienci.add(new Klient(i, bankier, new Random().nextInt(10) + 1));
            System.out.println(klienci.get(i));
        }

        for (Klient klient : klienci) {
            klient.start();
        }
    }
}

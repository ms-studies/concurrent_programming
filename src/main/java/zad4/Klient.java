package zad4;

import java.util.Random;

/**
 * Created by Marcin Szalek on 13.11.17.
 */
public class Klient extends Thread {

    private final int numerKlienta;
    private final Bankier bankier;
    private final int potrzeby;
    private int zasoby = 0;
    private boolean spelniony = false;

    public Klient(int numerKlienta, Bankier bankier, int potrzeby) {
        this.numerKlienta = numerKlienta;
        this.bankier = bankier;
        this.potrzeby = potrzeby;
    }

    @Override
    public void run() {
        print("Chcę pożyczyć " + (potrzeby - zasoby));
        int wartoscPozyczki = bankier.pozycz(potrzeby - zasoby);
        zasoby += wartoscPozyczki;
        print("Pożyczyłem " + wartoscPozyczki);
        try {
            sleep(new Random().nextInt(3000) + 1000);
            spelniony = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("Oddaję " + wartoscPozyczki);
        bankier.oddaj(wartoscPozyczki);
        zasoby -= wartoscPozyczki;
        print("Oddałem " + wartoscPozyczki);
    }

    @Override
    public String toString() {
        return "[KLIENT " + numerKlienta + ", zasoby: " + zasoby + ", potrzeby: " + potrzeby + ", spełniony: " + spelniony + "]: ";
    }

    private void print(String s) {
        System.out.println(this + s);
    }
}

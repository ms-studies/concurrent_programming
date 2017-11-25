package zad3;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by Marcin Szalek on 25.11.17.
 */
public class ProcesPrzyjmujacy extends Thread {

    private final Integer zasob;
    private final SynchronousQueue<Integer> synchronousQueue;

    public ProcesPrzyjmujacy(Integer zasob, SynchronousQueue<Integer> synchronousQueue) {
        this.zasob = zasob;
        this.synchronousQueue = synchronousQueue;
    }


    @Override
    public void run() {
        try {
            synchronousQueue.put(zasob);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            Integer odebranyZasob = null;
            while (odebranyZasob == null) {
                odebranyZasob = synchronousQueue.poll();
            }
            try {
                synchronousQueue.put(zasob);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

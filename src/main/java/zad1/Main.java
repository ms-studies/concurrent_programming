package zad1;

/**
 * Created by Marcin Szalek on 28.10.17.
 */
public class Main {



    public static void main(String[] args) {
        System.out.println("Hello world");
        Neuron n1 = new Neuron(1);
        Neuron n2 = new Neuron(2);
        Neuron n3 = new Neuron(3);
        n1.n1=n2;
        n1.n2=n3;
        n2.n1=n1;
        n2.n2=n3;
        n3.n1=n1;
        n3.n2=n2;
       // n1.semaphore.acquireUninterruptibly();
       // n2.semaphore.acquireUninterruptibly();
       // n3.semaphore.acquireUninterruptibly();
        n2.semaphore.acquireUninterruptibly(1);
        n3.semaphore.acquireUninterruptibly(2);
        n1.start();
        n2.start();
        n3.start();
    }
}

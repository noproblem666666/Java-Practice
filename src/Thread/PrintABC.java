package Thread;
import java.util.concurrent.Semaphore;
//使用信号量
class Printer {
    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);

    public void printA() throws InterruptedException {
        semaphoreA.acquire();
        System.out.print("A");
        semaphoreB.release();
    }

    public void printB() throws InterruptedException {
        semaphoreB.acquire();
        System.out.print("B");
        semaphoreC.release();
    }

    public void printC() throws InterruptedException {
        semaphoreC.acquire();
        System.out.print("C");
        semaphoreA.release();
    }
}

public class PrintABC {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    printer.printA();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    printer.printB();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    printer.printC();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}



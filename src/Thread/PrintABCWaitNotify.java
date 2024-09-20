package Thread;

import java.util.concurrent.atomic.AtomicInteger;
//使用计数器取模和wait，notify控制
class Printer1 {
    private int count = 0; // 0: A, 1: B, 2: C
    //这里也可以使用原子类
    private AtomicInteger n = new AtomicInteger(0);
    //Todo：wait()和notify()必须写在synchronized代码块中
    public synchronized void printA() throws InterruptedException {
        while (count % 3 != 0) {
            wait();
        }
        System.out.print("A");
        count++;
        notifyAll();
    }

    public synchronized void printB() throws InterruptedException {
        while (count % 3 != 1) {
            wait();
        }
        System.out.print("B");
        count++;
        notifyAll();
    }

    public synchronized void printC() throws InterruptedException {
        while (count % 3 != 2) {
            wait();
        }
        System.out.print("C");
        count++;
        notifyAll();
    }
}

public class PrintABCWaitNotify {
    public static void main(String[] args) {
        Printer1 printer = new Printer1();

        Thread t1 = new Thread(() -> {
            try {
                while (true) {
                    printer.printA();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                while (true) {
                    printer.printB();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                while (true) {
                    printer.printC();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}


package Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

//两个线程交替打印AB
public class PrintOneByOne {
    public static void doubleDetection(int maxNum) throws InterruptedException {
        //判断两个线程是否完成了打印
        CountDownLatch countDownLatch = new CountDownLatch(2);
        //指定要加锁的对象
        Object lock = new Object();
        //原子操作类，保证线程安全
        AtomicInteger count = new AtomicInteger();
        new Thread(() -> {
            while(count.get()<maxNum-1){
                //避免不是自己打印的时候,抢到锁一直无法释放
                if(count.get()%2!=0) continue;
                synchronized (lock){
                    if(count.get()%2==0){
                        System.out.println(count.get()+": A");
                        count.getAndIncrement();
                    }
                }
            }
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            while(count.get()<maxNum){
                if(count.get()%2==0) continue;
                synchronized (lock){
                    if(count.get()%2!=0){
                        System.out.println(count.get()+": B");
                        count.getAndIncrement();
                    }
                }
            }
            countDownLatch.countDown();
        }).start();
        //执行完成
        countDownLatch.await();
    }

    //使用wait()和notify()实现
    public static void waitAndNotify(int maxNum) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Object lock = new Object();
        AtomicInteger count = new AtomicInteger();
        new Thread(()->{
            while(count.get()<maxNum){
                synchronized (lock){
                    try{
                        lock.wait();
                    }catch(InterruptedException e){
                        throw new RuntimeException();
                    }
                    System.out.println(count.get() + ": B");
                    count.getAndIncrement();
                    lock.notify();
                }
            }
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            while(count.get()<maxNum-1){
                synchronized (lock){
                    //这里是先打印再释放，确保不会同时wait卡死
                    System.out.println(count.get() + ": A");
                    count.getAndIncrement();
                    lock.notify();
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        throw new RuntimeException();
                    }
                }
            }
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
    }
}

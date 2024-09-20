package LockAndSynchronized;

import java.util.concurrent.atomic.AtomicReference;

//在Java中实现一个低成本的自旋锁可以使用volatile关键字和while循环。
// 自旋锁的基本思想是让线程在尝试获取锁时，持续循环检查锁的状态，
// 而不是进行线程上下文切换。这种方法在锁竞争不激烈时是高效的，但如果竞争严重，可能会导致CPU资源浪费。
public class SpinLock {
    //Todo：使用volatile确保标志能被多线程看见
    private volatile boolean isLocked = false;
    //Todo:使用AtomicReference<Boolean>和compareAndSet()方法更加严谨，确保操作线程安全性
    private final AtomicReference<Boolean> lock = new AtomicReference<>(false);

    public void lock() {
        while (true) {
            // 尝试获取锁
            if (!isLocked) {
                // 如果锁没有被占用，则占用锁
                if (!tryLock()) {
                    // 如果失败，继续自旋
                    continue;
                }
                break; // 成功获取锁，退出循环
            }
        }
    }

    private boolean tryLock() {
        if (!isLocked) {
            isLocked = true; // 获取锁
            return true; // 成功获取锁
        }
        return false; // 锁已被占用
    }

    public void unlock() {
        isLocked = false; // 释放锁
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable task = () -> {
            spinLock.lock();
            try {
                // 访问共享资源
                System.out.println(Thread.currentThread().getName() + " is working.");
                // 模拟一些工作
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                spinLock.unlock();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}


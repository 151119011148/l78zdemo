package com.scofen.jdk.threads;

/**
 * Create by  GF  in  16:52 2019/1/8
 * Description:不可重入锁
 * Modified  By:
 */
public class MyUnReentrantLock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {

        while (isLocked) {
            wait();
        }
        isLocked = true;
        System.out.println(Thread.currentThread().getName() +"获得了锁");
    }

    public synchronized void unlock() {
        System.out.println(Thread.currentThread().getName() +"释放了锁");
        isLocked = false;
        notify();
    }


    public Runnable getRunnable() {
        return () -> {
                System.out.println(Thread.currentThread().getName()+"启动...");
                    try {
                        lock();
                        System.out.println(Thread.currentThread().getName() + "开始重入...");
                        entrant();
                        System.out.println(Thread.currentThread().getName() + "重入成功...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        unlock();
            };
    }

    private  void entrant() {
        try {
            lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        unlock();
    }


}

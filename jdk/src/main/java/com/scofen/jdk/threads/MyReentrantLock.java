package com.scofen.jdk.threads;

/**
 * Create by  GF  in  9:03 2019/1/9
 * Description:
 * Modified  By:
 */
public class MyReentrantLock {

    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount ++;
        lockedBy = thread;
        System.out.println(thread.getName() + "获得了锁,重入次数为：" + lockedCount);
    }

    public synchronized void unlock() {
        Thread thread = Thread.currentThread();
        if (thread== this.lockedBy) {
            lockedCount --;
            System.out.println(thread.getName() + "释放了锁,重入次数为：" + lockedCount);
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }


    protected Runnable getRunnable() {
        return () -> {
            System.out.println(Thread.currentThread().getName() + "启动...");
            try {
                lock();
                entrant();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unlock();
        };
    }

    private void entrant() {
        try {
            lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        unlock();
    }

}

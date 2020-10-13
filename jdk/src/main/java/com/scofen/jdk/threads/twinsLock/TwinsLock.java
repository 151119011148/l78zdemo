package com.scofen.jdk.threads.twinsLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Create by  GF  in  10:58 2020/2/15
 * Description:
 * Modified  By:
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {

            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " locked");
                    try {
                        SleepUtils.second(1);
                        SleepUtils.second(1);

                    } finally {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + " unlock");
                    }

                }
            }

        }
        //启动10个线程
        for (int i = 0; i < 4; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        //每隔一秒换行
        for (int i = 0; i < 100; i++) {
            SleepUtils.second(1);
            System.out.println();
        }


    }

    @Override
    public void lock() {
        sync.acquireShared(1);

    }

    @Override
    public void unlock() {
        sync.releaseShared(1);

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) > 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit)
            throws InterruptedException {
        return false;
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large zere");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                System.out.println(Thread.currentThread().getName() + "尝试获取锁，getState() = " + current);
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }

            }
        }


    }

    public static class SleepUtils {
        public static final void second(long sec) {
            try {
                TimeUnit.SECONDS.sleep(sec);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}

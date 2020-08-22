package com.scofen.jdk.threads;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  15:14 2019/1/11
 * Description:
 * Modified  By:
 */
public class FairAndUnfairEffectiveTest {

    private static Lock fairLock = new ReentrantLockMine(true);
    private static Lock unfairLock = new ReentrantLockMine(false);

    @Test
    public void test() throws Exception {
        //unfair();//16554ms
        fair();//24761ms
    }


    public  void unfair() throws BrokenBarrierException, InterruptedException {
        testLock("非公平锁", unfairLock);
    }
    public  void fair() throws BrokenBarrierException, InterruptedException {
        testLock("公平锁", fairLock);
    }

    private void testLock(String type, Lock lock) throws InterruptedException {

        long start = System.currentTimeMillis();
        //10个线程执行完毕时，执行Time线程统计执行时间
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Time(type, start));

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() ->{
                for (int j = 0; j < 100000; j ++) {
                    lock.lock();
                    try {
                        String currentThreadName = Thread.currentThread().getName();
                        String waitThreadNames = JSON.toJSONString(((ReentrantLockMine) lock)
                                .getQueuedThreads()
                                .stream()
                                .map(Thread::getName)
                                .collect(Collectors.toList()));
                        System.out.println("获取锁的当前线程[" + currentThreadName+ "], 同步队列中的线程" + waitThreadNames);
                    } finally {
                        lock.unlock();
                    }
                }
                try {
                    //计数器+1，直到10个线程都到达
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.setName(String.valueOf(i));
            thread.start();
        }
        Thread.sleep(30000);
    }



    private static class ReentrantLockMine extends ReentrantLock {
        public ReentrantLockMine(boolean fair) {
            super(fair);
        }
        //重新实现ReentrantLock类是为了重写getQueuedThreads方法，便于我们试验的观察
        @Override
        protected Collection<Thread> getQueuedThreads() {
            //获取同步队列中的线程
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }


    private static class Time implements Runnable {     //用于统计时间
        private long start ;
        private String lockType;

        public Time(String lockType, long start) {
            this.start = start;
            this.lockType = lockType;
        }

        public void run() {
            System.out.println(lockType + "耗时:" + String.valueOf(System.currentTimeMillis() - start) + "ms");
        }
    }
}

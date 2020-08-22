package com.scofen.jdk.threads;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  11:01 2019/1/11
 * Description:
 * Modified  By:
 */
public class FairAndUnfairUseTest {

    private static Lock fairLock = new ReentrantLockMine(true);
    private static Lock unfairLock = new ReentrantLockMine(false);

    @Test
    public void test() throws InterruptedException {
        unfair();
        fair();
    }


    public void unfair() throws InterruptedException {
        List unfairList = testLock("非公平锁", unfairLock);
        System.out.println(unfairList.toString());
    }


    public void fair() throws InterruptedException {
        List fairList = testLock("公平锁", fairLock);
        System.out.println(fairList.toString());
    }

    private List<String> testLock(String type, Lock lock) throws InterruptedException {
        List<String> lockList = new Vector<>();
        System.out.println(type);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    lock.lock();
                    try {
                        Thread.sleep(100);
                        String currentThread = Thread.currentThread().getName();
                        lockList.add(currentThread);
                        String waitThreads = JSON.toJSONString(((ReentrantLockMine) lock)
                                .getQueuedThreads()
                                .stream()
                                .map(item -> item.getName())
                                .collect(Collectors.toList()));
                        System.out.println("获取锁的当前线程[" + currentThread + "], 同步队列中的线程" + waitThreads);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            thread.setName(String.valueOf(i));
            thread.start();
        }
        Thread.sleep(3000);
        return lockList;
    }

    //重新实现ReentrantLock类是为了重写getQueuedThreads方法，便于我们试验的观察
    private static class ReentrantLockMine extends ReentrantLock {
        public ReentrantLockMine(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {   //获取同步队列中的线程
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

}

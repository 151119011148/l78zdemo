package com.scofen.jdk.threads;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Create by  GF  in  15:33 2019/1/16
 * Description:
 * Modified  By:
 */
public class MyAQS {

    //静态内部类，继承AQS
    private static class SyncLock extends AbstractQueuedSynchronizer {
        //是否处于占用状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        //当状态为0的时候获取锁，CAS操作成功，则state状态为1，
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        //释放锁，将同步状态置为0
        protected boolean tryRelease(int releases) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }
    //同步对象完成一系列复杂的操作，我们仅需指向它即可
    private final SyncLock syncLock = new SyncLock();
    //加锁操作，代理到acquire（模板方法）上就行，acquire会调用我们重写的tryAcquire方法
    public void lock() {
        syncLock.acquire(1);
    }
    public boolean tryLock() {
        return syncLock.tryAcquire(1);
    }
    //释放锁，代理到release（模板方法）上就行，release会调用我们重写的tryRelease方法。
    public void unlock() {
        syncLock.release(1);
    }
    public boolean isLocked() {
        return syncLock.isHeldExclusively();
    }


    private static CyclicBarrier barrier = new CyclicBarrier(31);
    private static int a = 0;
    private static MyAQS myAQS = new MyAQS();

    public static void main(String[] args) {
        //说明:我们启用30个线程，每个线程对i自加10000次，同步正常的话，最终结果应为300000；
        //未加锁前
        for(int i=0;i<30;i++){
            Thread t = new Thread(() -> {
                    for(int j=0;j<10000;j ++){
                        notAtomicIncrement();//没有同步措施的a++；
                    }
                    try {
                        barrier.await();//等30个线程累加完毕
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            });
            t.start();
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("加锁前，a="+a);

        //加锁后
        barrier.reset();//重置CyclicBarrier
        a=0;
        for(int i=0;i<30;i++){
            new Thread(() -> {
                    for(int j=0;j<10000;j++){
                        atomicIncrement();//a++采用Mutex进行同步处理
                    }
                    try {
                        barrier.await();//等30个线程累加完毕
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }).start();
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("加锁后，a="+a);
    }
    /**
     * 没有同步措施的a++
     * @return
     */
    public static void notAtomicIncrement(){
        a++;
    }
    /**
     * 使用自定义的MyAQS进行同步处理的a++
     */
    public static void atomicIncrement(){
        myAQS.lock();
        a++;
        myAQS.unlock();
    }












}

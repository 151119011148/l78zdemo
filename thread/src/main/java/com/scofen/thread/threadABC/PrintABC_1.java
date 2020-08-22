package com.scofen.thread.threadABC;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by  GF  in  19:53 2019/3/7
 * Description:
 * Modified  By:
 */
public class PrintABC_1 extends Thread{
    private final AtomicInteger synObj;
    private int count;
    private String s;
    private int flag;
    private int total = 0;

    private Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();


    public PrintABC_1(int count,AtomicInteger atomicInteger,String s,int flag) {
        this.count = count;
        this.synObj = atomicInteger;
        this.s = s;
        this.flag = flag;
    }
    public void run() {
        while(true) {
            lock.lock();
                if(synObj.intValue()%3 == flag) {
                    total++;
                    synObj.set(synObj.intValue()+1);
                    System.out.println(s);
                    condition.signalAll();
                    if(total == count) {
                        break;
                    }
                }else {
                    try{
                        condition.await();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            lock.unlock();
        }
    }
    public static void main(String[]args) throws Exception {
        AtomicInteger synObj = new AtomicInteger(0);
        PrintABC_1 a = new PrintABC_1(10,synObj,"A",0);
        PrintABC_1 b = new PrintABC_1(10,synObj,"B",1);
        PrintABC_1 c = new PrintABC_1(10,synObj,"C",2);
        a.start();
        b.start();
        c.start();
    }
}

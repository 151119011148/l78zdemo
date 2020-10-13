package com.scofen.jdk.threads.threadABC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by  GF  in  14:48 2019/3/5
 * Description:
 * Modified  By:
 */
public class PrintABC_3 extends Thread{
    private final AtomicInteger synObj;
    private int count;
    private String s;
    private int flag;
    private int total = 0;

    public PrintABC_3(int count,AtomicInteger atomicInteger,String s,int flag) {
        this.count = count;
        this.synObj = atomicInteger;
        this.s = s;
        this.flag = flag;
    }
    public void run() {
        while(true) {
            synchronized(synObj) {
                if(synObj.intValue()%3 == flag) {
                    total++;
                    //synObj.set(synObj.intValue()+1);
                    synObj.incrementAndGet();
                    System.out.println(s);
                    synObj.notifyAll();
                    if(total == count) {
                        break;
                    }
                }else {
                    try{
                        synObj.wait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[]args) throws Exception {
        AtomicInteger synObj = new AtomicInteger(0);
        PrintABC_3 a = new PrintABC_3(10,synObj,"A",0);
        PrintABC_3 b = new PrintABC_3(10,synObj,"B",1);
        PrintABC_3 c = new PrintABC_3(10,synObj,"C",2);
        a.start();
        b.start();
        c.start();
    }
}

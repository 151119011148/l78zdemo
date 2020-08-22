package com.scofen.jdk.threads;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Create by  GF  in  18:51 2019/7/13
 * Description:创建线程3种方式
 * 1继承Thread类（本质上实现的runnable接口）
 * 2实现runnable接口
 * 3实现callable接口 带返回值的
 * 4线程池取
 * Modified  By:
 */
public class ThreadTest extends Thread{

    @Override
    public void run() {
        System.out.println("线程启动~");
    }

    @Test
    public  void test() throws Exception {
        new ThreadTest().start();
        new Thread(new ThreadTest()).start();
        System.out.println(new ThreadTest2().call());
        Future future = Executors.newSingleThreadExecutor().submit(new ThreadTest2());
        System.out.println(future.get());
        Thread.sleep(1000);
    }


    public class ThreadTest2 implements Callable{

        @Override
        public Object call() throws Exception {
            return "Callable启动~";
        }
    }
}

/**
 * jps
 * jstack pid
 */
  class TreadStatus{

    public static void main(String[] args) {

        new Thread(() -> {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "time-waiting-thread").start();

        new Thread(() -> {
            while (true){
                synchronized (TreadStatus.class){
                    try {
                        TreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "time-wait-thread").start();

        //block
        new Thread(new Blocked(), "blocked-tread-1").start();
        new Thread(new Blocked(), "blocked-tread-2").start();
    }
}

 class Blocked extends Thread{

    @Override
    public void run() {
        synchronized (Blocked.class){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 1.Thread.interrupted();
 * 2中断阻塞状态的线程，会抛异常，并复位
 */
class ThreadInterruptedAndRollback{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            while (true){//默认false
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    Thread.interrupted();//复位 恢复到初始状态
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.isInterrupted();

    }

}
package com.scofen.jdk.threads;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

/**
 * Create by  GF  in  20:25 2019/1/14
 * Description:令牌桶
 * Modified  By:
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        //Semaphore semaphore = new Semaphore(5, false);
        Semaphore semaphore = new Semaphore(5, true);
        for (int i = 0 ; i < 10 ; i ++){
            new DoAnything(i, semaphore).start();
        }
    }


    @AllArgsConstructor
    static class DoAnything extends Thread{
        private int number;
        private Semaphore semaphore;

        @Override
        public void run() {
            try {
                semaphore.acquire();//获取第一个令牌
                System.out.println("第"+ number + "个线程得到令牌---");
                Thread.sleep(2000);
                semaphore.release();
                System.out.println("第"+ number + "个线程释放令牌===");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

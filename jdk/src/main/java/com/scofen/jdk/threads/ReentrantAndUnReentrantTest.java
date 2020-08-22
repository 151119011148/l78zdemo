package com.scofen.jdk.threads;



/**
 * Create by  GF  in  9:17 2019/1/9
 * Description:
 * Modified  By:
 */
public class ReentrantAndUnReentrantTest {


      public static void main(String[] args) {

        MyUnReentrantLock unReentrantLock = new MyUnReentrantLock();
         MyReentrantLock reentrantLock = new MyReentrantLock();
         Thread[] threadArray = new Thread[10];
/*         for (int i = 0; i < (threadArray.length >> 1); i++) {
             threadArray[i] = new Thread(reentrantLock.getRunnable());
             threadArray[i].start();
         }*/
        for (int i = 9; i > 4; i --) {
             threadArray[i] = new Thread(unReentrantLock.getRunnable());
             threadArray[i].start();
         }
     }

}

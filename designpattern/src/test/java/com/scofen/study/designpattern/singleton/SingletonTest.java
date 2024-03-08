package com.scofen.l78z.designpattern.singleton;

import com.scofen.designpattern.singleton.LazySix;
import org.junit.Test;


import java.util.concurrent.CountDownLatch;

public class SingletonTest {

    int threadCount = 1024 * 10;
    CountDownLatch latch = new CountDownLatch(threadCount);

    @Test
    public void testSingleton(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i ++){
            new Thread(() ->{
                //Object instance = HungryOne.getInstance();//4113   3455  3540
                //Object instance = HungryTwo.getInstance();//4013  3252  2392
                //Object instance = LazyOne.getInstance();//3840   3682    3388
                //Object instance = LazyTwo.getInstance();//4247   4513   3706
                //Object instance = LazyThree.getInstance();//4201   3619   3522
                //Object instance = LazyFour.getInstance();//4235   4271   4750
                //Object instance = LazyFive.getInstance();//3500   3379   3595
                Object instance = LazySix.getInstance();//4412   3619   3522
                System.out.println(System.currentTimeMillis() + ":" + instance);
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            latch.countDown();
        }
        long end = System.currentTimeMillis();
        try {
            Thread.sleep(6000);
            System.out.println("创建单例总耗时："   + (end - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
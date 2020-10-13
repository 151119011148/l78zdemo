package com.scofen.jdk.threads.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Create by  GF  in  15:39 2019/3/11
 * Description:
 * 漏桶，令牌桶  guava
 * Modified  By:
 */
public class Token {

    //令牌桶
    RateLimiter rateLimiter1 = RateLimiter.create(10/*00*/); //qps 1000

    //漏桶
    RateLimiter rateLimiter2 = RateLimiter.create(1000, 10, TimeUnit.MILLISECONDS);

    public void doPay(){
        if (rateLimiter1.tryAcquire()){
            System.out.println(Thread.currentThread().getName() + "----开始支付~");
        }else {
            System.out.println("系统繁忙~");
        }
    }

    @Test
    public void testPay() throws IOException {
        Token token = new Token();
        CountDownLatch latch = new CountDownLatch(1);
        Random random = new Random(10);
        for (int i = 0; i < 20; i++){
            new Thread(
                    () ->{
                        try {
                            latch.await();
                            Thread.sleep(random.nextInt(1000));
                            token.doPay();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
        latch.countDown();
        System.in.read();
    }
}

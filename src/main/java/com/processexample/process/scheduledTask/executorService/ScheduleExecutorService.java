package com.processexample.process.scheduledTask.executorService;


import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by  GF  in  8:51 2018/8/2
 * Description:
 * Modified  By:
 */
public class ScheduleExecutorService {
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws Exception {
        final String jobID = "my_job_1";
        final AtomicInteger count = new AtomicInteger(0);
        final Map<String, Future> futures = new ConcurrentHashMap<>();

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final int[] k = {1};
        Future future = scheduler.scheduleWithFixedDelay(()->{
                if (count.get() > 3 && k[0] >5) {
                    Future thisFuture = futures.get(jobID);
                    if (thisFuture != null) thisFuture.cancel(true);
                    countDownLatch.countDown();
                }else {
                    System.out.println(count.getAndIncrement());
                    System.out.println(k[0]++);
                }
            }, 0, 1, TimeUnit.SECONDS);

        futures.put(jobID, future);
        System.out.println("主线程~~~~~~~~~~~~~~~~·await前");
        countDownLatch.await();
        scheduler.shutdown();
        System.out.println("主线程~~~~~~~~~~~~~~~~·await后");
    }
}

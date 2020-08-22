package com.processexample.process.scheduledTask.springScheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Instant;

/**
 * Create by  GF  in  17:08 2018/6/22
 * Description:
 * Modified  By:
 */
@Component
@Slf4j
public class ScheduledTasks {

    private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;

   // @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
    public void testFixDelay() {
        log.info("===当前线程id为:" + Thread.currentThread().getId() + "===");
        log.info("===[MyTaskA-间隔调度===" + Instant.now());
        //log.info(MessageFormat.format("===fixedDelay: 第{0}次执行方法===", fixedDelayCount++));
    }

   /* @Scheduled(fixedRate = 5000)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
    public void testFixedRate() {
        System.out.println("-------------当前线程id为:" + Thread.currentThread().getId() + "-------------");
        System.out.println(MessageFormat.format("===fixedRate: 第{0}次执行方法", fixedRateCount++));
    }*/

    //@Scheduled(initialDelay = 1000, fixedRate = 5000)   //initialDelay = 1000表示延迟1000ms执行第一次任务
    public void testInitialDelay() {
        log.info("===当前线程id为:" + Thread.currentThread().getId() + "===");
        log.info(MessageFormat.format("===initialDelay: 第{0}次执行方法", initialDelayCount++));
    }

/*    @Scheduled(cron = "0/10 *  * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void testCron() {
        log.info("===当前线程id为:" + Thread.currentThread().getId() + "===");
        log.info("===[MyTaskB-定时调度调度===" + Instant.now());
        log.info(MessageFormat.format("===initialDelay: 第{0}次执行方法===", cronCount++));
    }*/

    public static void main(String[] args){


            //new ScheduledTasks().testCron();
            new ScheduledTasks().testInitialDelay();

    }

}

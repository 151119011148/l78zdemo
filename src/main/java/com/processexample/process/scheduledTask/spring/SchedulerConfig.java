package com.processexample.process.scheduledTask.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * Create by  GF  in  17:38 2018/6/22
 * Description:
 * Modified  By:
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    //开启一个线程调度池

    @Override

    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));

    }

}

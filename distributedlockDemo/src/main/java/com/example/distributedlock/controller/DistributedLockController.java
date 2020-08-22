package com.example.distributedlock.controller;

import com.example.distributedlock.service.DistributedLockService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Create by  GF  in  16:47 2018/6/4
 * Description:
 * Modified  By:
 */
@Slf4j
@RestController
@RequestMapping(value = "/distributedLock")
public class DistributedLockController {

    private int count = 100;//100张票
    private static final String distributedLockId = "/DistributedLock";

    @Resource(name = "mysqlLockService")
    private DistributedLockService mysqlLockService;

    //redis指令
    @Resource(name = "redisLockServiceWithCommand")
    //lua脚本
    //@Resource(name = "redisLockServiceWithLua")
    private DistributedLockService redisLockService;

    //@Resource(name = "zookeeperLockServiceWithCurator")
    @Resource(name = "zookeeperLockService")
    private DistributedLockService zookeeperLockService;

    @PostMapping(value ="/mysqlLock")
    //@Explain(name= "mysql分布式锁", logLv = LogLevel.INFO)
    public String mysqlLock(){
        SafeTicketRunnable safeTicketRunnable = new SafeTicketRunnable(mysqlLockService);
        threadsStart(safeTicketRunnable);
        return "success";
    }

    @PostMapping(value ="/redisLock")
    //@Explain(name= "redis分布式锁", logLv = LogLevel.INFO)
    public String redisLock(){
        SafeTicketRunnable safeTicketRunnable = new SafeTicketRunnable(redisLockService);
        threadsStart(safeTicketRunnable);
         return "success";
    }
    @PostMapping(value ="/zookeeperLock")
    //@Explain(name= "zookeeper分布式锁", logLv = LogLevel.INFO)
    public String zookeeperLock() {
        SafeTicketRunnable safeTicketRunnable = new SafeTicketRunnable(zookeeperLockService);
        threadsStart(safeTicketRunnable);
        return "success";
    }

    private void threadsStart(SafeTicketRunnable safeTicketRunnable) {
        Thread thread1 = new Thread(safeTicketRunnable, "窗口1");
        Thread thread2 = new Thread(safeTicketRunnable, "窗口2");
        Thread thread3 = new Thread(safeTicketRunnable, "窗口3");
        Thread thread4 = new Thread(safeTicketRunnable, "窗口4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    class SafeTicketRunnable implements Runnable{

        private DistributedLockService distributedLockService;

         SafeTicketRunnable(DistributedLockService distributedLockService) {
            this.distributedLockService = distributedLockService;
        }

        @Override
        public void run() {
            while (count > 0){
                distributedLockService.lock(distributedLockId);
                log.info(Thread.currentThread().getName() + "售出第" + count -- +"张票");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }finally {
                    distributedLockService.releaseLock();
                }
            }
        }
    }



}

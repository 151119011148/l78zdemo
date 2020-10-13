package com.scofen.distributedlock.service.zookeeper;

/**
 * Create by  GF  in  15:07 2019/2/13
 * Description:
 * Modified  By:
 */

import com.scofen.distributedlock.service.AbstractDistributedLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import java.util.concurrent.TimeUnit;


/**
 * Create by  GF  in  21:58 2019/1/27
 * Description:
 * Modified  By:
 */
@ThreadSafe
@Slf4j
@Service("zookeeperLockServiceWithCurator")
public class ZookeeperLockServiceImplWithCurator extends AbstractDistributedLockService {


    private   InterProcessMutex lock;

    private  String clientName;

    @Value("${zookeeper.IP_PORT}")
    public   String ipAndPort;


    @PostConstruct
    private void init(){
        CuratorFramework curator = CuratorFrameworkFactory.newClient(ipAndPort,
                new RetryOneTime((int) SLEEP_TIMES));
        curator.start();
        lock = new InterProcessMutex(curator,lockKeyByZookeeper);
    }

    @Override
    public boolean tryLock(String key) {
        boolean result = false;
        try {
              result = lock.acquire(TIMEOUT_TIMES, TimeUnit.SECONDS);
        } catch (Exception e) {
            //log.info(" could not acquire the lock");
            return result;
        }
        return result;
    }

    @Override
    public boolean releaseLock(String key) {
        try {
            lock.release();
        } catch (Exception e) {
            //log.info(" releasing the lock");
            return false;
        }
        return true;
    }

    @Override
    public boolean releaseLock() {
        return releaseLock(lockKeyByZookeeper);
    }


}

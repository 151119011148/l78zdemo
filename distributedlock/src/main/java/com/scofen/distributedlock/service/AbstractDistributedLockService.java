package com.scofen.distributedlock.service;


/**
 * Create by  GF  in  11:06 2019/1/31
 * Description:
 * Modified  By:
 */
public abstract class AbstractDistributedLockService implements DistributedLockService {

    @Override
    public boolean lock(String key) {
        return lock(key, TIMEOUT_TIMES, RETRY_TIMES, SLEEP_TIMES);
    }

    @Override
    public boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_TIMES, retryTimes, SLEEP_TIMES);
    }

    @Override
    public boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_TIMES, retryTimes, sleepMillis);
    }

    @Override
    public boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_TIMES);
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_TIMES);
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        if (tryLock(key)) {
            return true;
        } else {
            try {
                Thread.sleep(SLEEP_TIMES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock(key, expire, retryTimes, sleepMillis);
        }
        return false;
    }

    public abstract boolean tryLock(String key);

    @Override
    public boolean releaseLock(){
        return releaseLock(lockKeyByZookeeper);
    }



}

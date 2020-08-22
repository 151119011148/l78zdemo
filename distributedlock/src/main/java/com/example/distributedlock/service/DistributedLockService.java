package com.example.distributedlock.service;

public interface DistributedLockService {

    public static final String lockKeyByRedis = "DistributeLockByRedis";

    public static final String lockKeyByZookeeper = "/DistributeLockByZookeeper";

    public static final long TIMEOUT_TIMES = 3;

    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    public static final long SLEEP_TIMES = 1;

    public boolean lock(String key);

    public boolean lock(String key, int retryTimes);

    public boolean lock(String key, int retryTimes, long sleepMillis);

    public boolean lock(String key, long expire);

    public boolean lock(String key, long expire, int retryTimes);

    public boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    public boolean tryLock(String key);

    public boolean releaseLock(String key);

    public boolean releaseLock();


}

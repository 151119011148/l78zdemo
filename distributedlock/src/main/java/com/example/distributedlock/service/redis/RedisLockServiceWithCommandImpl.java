package com.example.distributedlock.service.redis;

import com.example.distributedlock.service.AbstractDistributedLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create by  GF  in  13:45 2019/1/28
 * Description:
 * Modified  By:
 */
@Slf4j
@Service("redisLockServiceWithCommand")
public class RedisLockServiceWithCommandImpl extends AbstractDistributedLockService {

    @Resource
    private    RedisTemplate<String, String> redisTemplate;

    // 线程变量
    private static ThreadLocal<String> threadKeyId = new ThreadLocal<>();


    public boolean tryLock(String key) {
        Assert.notNull(key, "key can't be null!");
        BoundValueOperations<String,String> keyBoundValueOperations = redisTemplate.boundValueOps(key);
        // 如果上次拿到锁的是自己，则本次也可以拿到锁：实现可重入
        String oldValue = keyBoundValueOperations.get();
        // 根据传入的值，判断用户是否持有这个锁
        if(oldValue != null && oldValue.equals(String.valueOf(threadKeyId.get()))){
            log.info("实现重入，threadKeyId为：" + threadKeyId.get() + "，redisValue为：" + oldValue);
            // 重置过期时间
            keyBoundValueOperations.expire(TIMEOUT_TIMES, TimeUnit.SECONDS);
            return true;
        }
        String newValue = UUID.randomUUID().toString().replaceAll("-","");
        threadKeyId.set(newValue);
        boolean result = keyBoundValueOperations.setIfAbsent(threadKeyId.get());
        if(result){
            log.info("redis的setnx操作结果为：" + result + "，threadKeyId为：" + threadKeyId.get() + "，redisValue为：" + newValue);
            // 每次获取锁时，必须重新生成id值
            // 设置value，再设置过期日期，否则过期日期无效
            keyBoundValueOperations.set(newValue);
            log.info("redisValue为：" + newValue + "，threadKeyId为：" + threadKeyId.get() + "，redisValue为：" + newValue);
            // 为了避免一个用户拿到锁后，进行过程中没有正常释放锁，这里设置一个默认过期实际，这段非常重要，如果没有，则会造成死锁
            keyBoundValueOperations.expire(TIMEOUT_TIMES, TimeUnit.SECONDS);
            // 拿到锁后，跳出循环
            return true;
        }
        return false;
        /**
         * 第二种方式
         */
        //return redisTemplate.opsForValue().setIfAbsent(lockKeyByRedis, threadKeyId.get());
    }


    @Override
    public boolean releaseLock(String key) {
        return redisTemplate.delete(lockKeyByRedis);

    }


}

package com.scofen.distributedlock.service.redis;

import com.scofen.distributedlock.service.AbstractDistributedLockService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Create by  GF  in  13:45 2019/1/28
 * Description:
 * Modified  By:
 */
@Slf4j
@Service("redisLockServiceWithLua")
public class RedisLockServiceWithLuaImpl extends AbstractDistributedLockService {

    @Resource
    private    RedisTemplate<String, String> redisTemplate;
    private static  DefaultRedisScript<Long> lockScript; // 锁脚本
    private static final String lockString = "local key     = KEYS[1]\n" +
            "local newValue = KEYS[2]\n" +
            "local ttl     = ARGV[1]\n" +
            "local lockSet = redis.call('setnx', key, newValue)\n" +
            "if lockSet == 1 then\n" +
            "  redis.call('expire', key, ttl)\n" +
            "else \n" +
            "  local value = redis.call('get', key)\n" +
            "  if(value == newValue) then\n" +
            "    lockSet = 1;\n" +
            "    redis.call('expire', key, ttl)\n" +
            "  end\n" +
            "end\n" +
            "return lockSet";
    private DefaultRedisScript<Long> unlockScript; // 解锁脚本
    private static final String unlockString = "if redis.call(\"get\", KEYS[1]) == ARGV[1] then\n" +
            "  return redis.call(\"del\", KEYS[1])\n" +
            "else\n" +
            "  return 0\n" +
            "end";

    // 线程变量
    private static ThreadLocal<String> threadKeyId = new ThreadLocal<>();



    public RedisLockServiceWithLuaImpl() {
        init();
    }

    @Override
    public boolean tryLock(String key) {
        String value = UUID.randomUUID().toString().replaceAll("-","");
        threadKeyId.set(value);
        Assert.notNull(key, "lockKeyByRedis can't be null!");
        Long result = redisTemplate.execute(lockScript, Collections.singletonList(key), value,String.valueOf(TIMEOUT_TIMES));
        return result == 1;
    }

    @Override
    public boolean releaseLock(String key) {
        Long result = redisTemplate.execute(unlockScript, Collections.singletonList(key), threadKeyId.get());
        return result == 1;
    }
    /**
     * 解析脚本（加锁，解锁）
     */
    private void init() {
        // Lock script
        lockScript = new DefaultRedisScript<>();
        //lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("com/scofen/distributedlock/lock.lua")));
        lockScript.setScriptText(unlockString);
        lockScript.setResultType(Long.class);

        // unlock script
        unlockScript = new DefaultRedisScript<>();
        unlockScript.setScriptText(lockString);
        //lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("com/scofen/distributedlock/lock.lua")));
        unlockScript.setResultType(Long.class);
    }

}

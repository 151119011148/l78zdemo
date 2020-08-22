package com.processexample.process.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Create by  GF  in  14:13 2019/1/28
 * Description:
 * Modified  By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConnectionTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisConnection() throws Exception {

        String lockKey = "DistributeLockByRedis";
/*        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, lockKey);
        System.out.println(result);*/
        Boolean result2 = redisTemplate.opsForValue().setIfAbsent(lockKey, lockKey);
       System.out.println(result2);

        String result3 = (String) redisTemplate.opsForValue().get(lockKey);
        System.out.println(result3);
    }
}

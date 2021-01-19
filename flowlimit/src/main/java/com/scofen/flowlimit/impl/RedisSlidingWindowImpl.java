/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.flowlimit.impl;

import com.google.common.collect.Lists;
import com.scofen.flowlimit.interfac.SlidingWindow;
import com.scofen.flowlimit.interfac.AbstractTokenBucket;

import javax.annotation.Resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * TODO
 *
 * @author scofen
 * @version V1.0
 * @since 2021-01-08 11:25
 */
public class RedisSlidingWindowImpl implements SlidingWindow, AbstractTokenBucket {

    @Resource
    private RedisTemplate redisTemplate;

    public static final String TIME_OUT = "1000";

    @Override
    public Boolean redisSlidingWindow(String key, Integer count){
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/rateLimit.lua")));
        redisScript.setResultType(Long.TYPE);

        Object result = redisTemplate.execute(redisScript, Lists.newArrayList(key), String.valueOf(count), TIME_OUT);

        if ((long) result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean execute(String key, Integer count) {
        return redisSlidingWindow(key, count);
    }

    @Override
    public Boolean redisTokenBucket(String key){
        return null;
    }

    @Override
    public Boolean guavaTokenBucket() {
        return null;
    }


    @Override
    public Boolean guavaSlidingWindow() {
        return null;
    }


}

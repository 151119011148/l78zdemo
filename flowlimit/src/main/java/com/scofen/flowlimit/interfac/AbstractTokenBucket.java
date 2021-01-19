/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.scofen.flowlimit.interfac;

/**
 * 令牌桶
 * 令牌桶算法(Token Bucket)随着时间流逝,系统会按恒定1/QPS时间间隔(如果QPS=100,则间隔是10ms)
 * 往桶里加入Token(想象和漏洞漏水相反,有个水龙头在不断的加水),如果桶已经满了就不再加了.
 * 新请求来临时,会各自拿走一个Token,如果没有Token可拿了就阻塞或者拒绝服务.
 *
 * 令牌桶的另外一个好处是可以方便的改变速度.
 * 一旦需要提高速率,则按需提高放入桶中的令牌的速率.
 * 一般会定时(比如100毫秒)往桶中增加一定数量的令牌,
 * 有些变种算法则实时的计算应该增加的令牌的数量.
 *
 * @author scofen
 * @version V1.0
 * @since 2021-01-08 10:48
 */

public interface AbstractTokenBucket extends FlowLimiter {


    public Boolean redisTokenBucket(String key);

    public Boolean guavaTokenBucket();


}

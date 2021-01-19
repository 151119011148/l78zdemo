package com.scofen.flowlimit.impl.multi;

import com.scofen.flowlimit.impl.RedisSlidingWindowImpl;
import com.scofen.flowlimit.interfac.AbstractLimiterFactory;
import com.scofen.flowlimit.interfac.FlowLimiter;
import com.scofen.flowlimit.interfac.SlidingWindow;

import javax.annotation.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributedLimiter implements FlowLimiter {

    public static final String TIME_OUT = "1000";

    @Resource
    private FlowLimiter redisSlidingWindowImpl;

    @Override
    public Boolean execute(String route, Integer limit) {
        return redisSlidingWindowImpl.execute(route, limit);
    }



}

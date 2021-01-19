package com.scofen.flowlimit.configuration;

import com.scofen.flowlimit.advice.MethodRateLimiterAdvisor;
import com.scofen.flowlimit.advice.MethodRateLimiterBeforeInterceptor;
import com.scofen.flowlimit.impl.multi.DistributedLimiter;
import com.scofen.flowlimit.interfac.FlowLimiter;
import com.scofen.flowlimit.interfac.SlidingWindow;

import javax.annotation.Resource;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by wb-lz260260 on 2017/7/5.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class LimiterConfiguration {

    @Resource
    SlidingWindow slidingWindow;

    /**
     * 自动代理生成器
     * 这个类可以扫描所有的切面类，并为其自动生成代理。
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator() ;
    }

    @Bean
    public MethodRateLimiterBeforeInterceptor methodAroundInterceptor(){
        return new MethodRateLimiterBeforeInterceptor() ;
    }


    @Bean
    public MethodRateLimiterAdvisor methodAdvisor(){
        return new MethodRateLimiterAdvisor();
    }


    @Bean
    public DistributedLimiter distributedLimiter(){
        return new DistributedLimiter(slidingWindow) ;
    }

}

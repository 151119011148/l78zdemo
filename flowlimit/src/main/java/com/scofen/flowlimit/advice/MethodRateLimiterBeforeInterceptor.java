package com.scofen.flowlimit.advice;

import com.scofen.flowlimit.annotation.Limiter;
import com.scofen.flowlimit.interfac.FlowLimiter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 方法拦截器：执行前通知
 * @author scofen
 */
@Component
@Order
@Slf4j
public class MethodRateLimiterBeforeInterceptor implements MethodInterceptor {

    @Resource
    FlowLimiter distributedLimiter ;

    /**
     * 执行逻辑
     * @param methodInvocation
     * @return Object
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String route = "";
        int limit = 1;

        Method method = methodInvocation.getMethod();

        for (Annotation annotation : method.getAnnotations()) {
            /*
             * 如果方法具有Limiter注解，则需要把method，limit拿出来
             */
            if (annotation instanceof Limiter) {
                Limiter limiter = method.getAnnotation(Limiter.class);
                route = limiter.route();
                limit = limiter.limit();

                if(!distributedLimiter.execute(route , limit)) {
                    throw new Exception("访问太过频繁，请稍后再试！") ;
                }
            }
        }
        return methodInvocation.proceed() ;
    }

}

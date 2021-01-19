package com.scofen.flowlimit.advice;

import com.scofen.flowlimit.annotation.Limiter;
import com.scofen.flowlimit.interfac.FlowLimiter;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态切入点
 */
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
public class MethodRateLimiterAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Autowired
    MethodRateLimiterBeforeInterceptor advice;

    @PostConstruct
    public void init() {
        super.setAdvice(this.advice);
    }

    /**
     * 只有加了Limiter注解的才需求切入
     *
     * @param method
     * @param clazz
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method mod : methods) {
            Limiter methodAnnotation = mod.getAnnotation(Limiter.class);
            if (null != methodAnnotation) {
                log.info(String.format("======%s .. %s .. %s .. %s .. %s ======", method.getName(), this.getPointcut(),
                    Thread.currentThread().getName(), clazz.getCanonicalName(), mod.getName()));
                return true;
            }
        }
        return false;
    }

}
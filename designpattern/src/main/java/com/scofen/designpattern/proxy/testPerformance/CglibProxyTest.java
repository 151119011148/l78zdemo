package com.scofen.designpattern.proxy.testPerformance;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by  GF  in  13:49 2019/3/14
 * Description:
 * Cglib 动态代理 (cglib proxy)
 * Modified  By:
 */
public class CglibProxyTest implements MethodInterceptor {

    private CglibProxyTest() {}

    public static <T extends Test> Test newProxyInstance(Class<T> targetInstanceClazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetInstanceClazz);
        enhancer.setCallback(new CglibProxyTest());
        return (Test) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invokeSuper(obj, args);
    }
}

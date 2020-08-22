package com.scofen.study.designpattern.proxy.testPerformance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by  GF  in  13:49 2019/3/14
 * Description:
 * JDK 动态代理(dynamic proxy)
 *
 * Modified  By:
 */
public class DynamicProxyTest implements InvocationHandler {
    private Test target;

    private DynamicProxyTest(Test target) {
        this.target = target;
    }

    public static Test newProxyInstance(Test target) {
        return (Test) Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                new Class<?>[]{Test.class},
                new DynamicProxyTest(target));

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
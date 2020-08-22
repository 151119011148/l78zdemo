package com.scofen.jvm.jvmError;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by  GF  in  12:04 2019/3/3
 * Description:借助Cglib使方法区内存溢出(1.8无法实现，没有永久代)
 * VM  Args: -XX:PermSize=10m  -XX:MaxPermSize=10m
 */
public class MethodAreaOOM {

    static class OOMObject{}
    @Test
    public void methodAreaOOM(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, objects));
            enhancer.create();
        }
    }


}

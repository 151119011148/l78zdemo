package com.scofen.designpattern.proxy.dynamicProxy.jdk;


import com.scofen.designpattern.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create by  GF  in  16:39 2018/11/14
 * Description:
 * Modified  By:
 */
public class Job51 implements InvocationHandler {
    private Person target;

    //获取被代理人的资料
    public Object getInstance(Person target)throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理的对象的class是：" + clazz);
        //代理对象
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    };


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        publish(method, args);

        return null;
    }

    public void publish(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("我是前程无忧，你的性别是：" + this.target.getSex() + "。需要为您找一个工作~");
        System.out.println("开始发布您的需求。。。");
        System.out.println("--------------------------------");
        //this.target.findLove();
        method.invoke(this.target,args);
        System.out.println("--------------------------------");
        System.out.println("如果合适就准备面试入职~");
    }
}

package com.scofen.designpattern.proxy.myselfProxy;



import java.lang.reflect.Method;

/**
 * Create by  GF  in  16:22 2018/7/23
 * Description:
 * Modified  By:
 */
public class MyselfWomanMatchmaker implements MyselfInvocationHandler {

    private Person target;

    //获取被代理人的资料
    public Object getInstance(Person target)throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理的对象的class是：" + clazz);
        //代理对象
        return MyselfProxy.newProxyInstance(new MyselfClassLoader(),clazz.getInterfaces(),this);
    };

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("我是媒婆" );
        System.out.println("开始发布您的需求。。。");
        System.out.println("--------------------------------");
        //this.target.findLove();
        method.invoke(this.target,args);
        System.out.println("--------------------------------");
        System.out.println("如果合适就准备办事~");

        return null;
    }


}

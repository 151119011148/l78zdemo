package com.scofen.designpattern.proxy.dynamicProxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by  GF  in  15:09 2018/7/23
 * Description:
 * Modified  By:
 */
public class WomanMatchmaker implements MethodInterceptor {

    //疑问：好像并没有持有被代理对象的引用



    //获取被代理人的资料
    public Object getInstance(Class clazz)throws Exception{

        Enhancer enhancer = new Enhancer();
        //这一步告诉cglib，生成谁的子类，父类是谁
        enhancer.setSuperclass(clazz);
        //设置回调
        enhancer.setCallback(this);
        //第一步生成源代码，第二部编译成class文件，第三部加载到jvm并返回代理对象
        return enhancer.create();
    }

    /**
     * 同样是做了字节码重组这件事
     * 对于适用API用户来说业务无感
     * @param object
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("我是媒婆" );
        System.out.println("开始发布您的需求。。。");
        System.out.println("--------------------------------");
        //这个object的引用使我们Cglib给我们new出来的，是代理对象的子类
        //OOP ，子类创建之前，调用super（）方法
        //子类创建时，父类已经创建出来了，相当于间接持有父类引用
        //子类重写了父类的所有方法，修改子类的属性
        methodProxy.invokeSuper(object, objects);
        System.out.println("--------------------------------");
        System.out.println("如果合适就准备办事~");
        return null;
    }

}

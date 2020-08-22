package com.scofen.jvm.classLoader;

import org.junit.Test;

/**
 * Create by  GF  in  14:20 2019/3/1
 * Description:
 * Modified  By:
 */
public class JVMClassLoader {


    @Test
    public void testClassLoader1(){
        //爷爷辈类加载器
        //启动类加载器（Bootstrap）C++     在lib/rt.jar包里
        //java拿不到c的对象
        System.out.println(JVMClassLoader.class.getClassLoader().getParent().getParent());
        //父亲辈类加载器
        // 扩展类加载器（Extension）Java
        //在lib/ext/*.jar包里
        System.out.println(JVMClassLoader.class.getClassLoader().getParent());
        //自身类加载器
        //应用程序类加载器（AppClassLoader）也叫做系统类加载器，加载当前应用的classpath的所有类
        System.out.println(JVMClassLoader.class.getClassLoader());
        //Object类加载器
        System.out.println(Object.class.getClassLoader());
    }
}

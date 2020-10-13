package com.scofen.designpattern.singleton;

/**
 * Create by  GF  in  14:26 2019/3/13
 * Description:
 * 静态内部类[推荐用]
 * Modified  By:
 */
public class LazyFive {

    private LazyFive() {}

    private static class SingletonInstance {
        private static final LazyFive INSTANCE = new LazyFive();
    }

    public static LazyFive getInstance() {
        return SingletonInstance.INSTANCE;
    }
    /**
     * 静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，
     * 调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。

     类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了
     线程的安全性，在类进行初始化时，别的线程是无法进入的。
     */
}

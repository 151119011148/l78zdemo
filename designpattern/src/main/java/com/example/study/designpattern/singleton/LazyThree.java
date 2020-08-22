package com.scofen.study.designpattern.singleton;

/**
 * Create by  GF  in  14:12 2019/3/13
 * Description:
 * 懒汉式
 * (线程安全，同步代码块)[不可用]
 * Modified  By:
 */
public class LazyThree {

    private static LazyThree lazyThree;

    private LazyThree() {}

    public static LazyThree getInstance() {
        if (lazyThree == null) {
            synchronized (LazyThree.class) {
                lazyThree = new LazyThree();
            }
        }
        return lazyThree;
    }

}
